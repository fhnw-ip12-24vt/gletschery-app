package com.pi4j.catalog.components;

import java.awt.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.CRC32;

import com.pi4j.catalog.components.base.SerialActuator;

/**
Wiring
 - GND PBOE to GND power supply, common with GND of RPi
 - 5V  PBOE to external power supply
 - DAT PBOE to BCM14 on Rpi (pin 8 = UART TxD)
 - CLK PBOE to BCM15 on Rpi (pin 10 = UART RxD)
 */
public final class LedStrip extends SerialActuator {

    public enum Channel {
        C0(0), C1(1), C2(2), C3(3), C4(4), C5(5), C6(6), C7(7);

        private final byte channel;

        Channel(int channel) {
            this.channel = (byte) channel;
        }
    }

    private static final byte BYTES_PER_PIXEL = 3;
    private static final byte CH_WS2812_DATA  = 1;
    private static final byte CH_DRAW_ALL     = 2;

    private final Map<Channel, byte[]> colorBytes = new HashMap<>();
    private final Map<Channel, Double> percentages = new HashMap<>();

    private final ExecutorService stripHandler = Executors.newSingleThreadExecutor();

    private boolean bouncing = false;
    private boolean pulsate = false;

    public LedStrip(Channel channel, int numberOfLeds) {
        super(2_000_000);
        addChannel(channel, numberOfLeds);
    }

    public void addChannel(Channel channel, int numberOfLeds) {
        colorBytes.put(channel, new byte[numberOfLeds * BYTES_PER_PIXEL]);
        percentages.put(channel, 1.0);
    }

    public void pause(Duration pause){
        stripHandler.execute(() -> delay(pause));
    }

    public void sendColorToAllLeds(Channel channel, Color color) {
        stripHandler.execute(() -> {
            boolean someColorHasChanged = setColorToAllLeds(channel, color);
            if(someColorHasChanged){
                sendColors(channel);
            }
        });
    }

    public void sendAllLedsOff(Channel channel) {
        stripHandler.execute(() -> {
            boolean someColorHasChanged = setColorToAllLeds(channel, Color.black);
            if(someColorHasChanged){
                sendColors(channel);
            }
        });
    }

    public void startBouncing(Channel channel, Color backgroundColor, Color bounceColor, int paddleWidth, Duration pause){
        if(bouncing){
            return;
        }
        sendColorToAllLeds(channel, backgroundColor);
        pause(Duration.ofMillis(50));

        stripHandler.execute(() -> {
            bouncing = true;
            short leds = numberOfLeds(channel);
            int lastLed = 0;

            while(bouncing){
                for (int i = 0; i < leds; i+=paddleWidth) {
                    if(!bouncing){
                        break;
                    }
                    setColorSequence(channel, i, paddleWidth, bounceColor);
                    sendColors(channel);
                    if(!bouncing){
                        break;
                    }
                    lastLed = i;
                    delay(pause);
                    setColorSequence(channel, i, paddleWidth, backgroundColor);
                }
                for (int i = lastLed; i > paddleWidth; i-=paddleWidth) {
                    if(!bouncing){
                        break;
                    }
                    setColorSequence(channel, i - paddleWidth, paddleWidth,bounceColor);
                    sendColors(channel);
                    if(!bouncing){
                        break;
                    }
                    delay(pause);
                    setColorSequence(channel, i - paddleWidth, paddleWidth, backgroundColor);
                }
            }
        });
    }

    public void stopBouncing(){
        bouncing = false;
    }

    public void startPulsation(Channel channel, Color color){
        if(pulsate){
            return;
        }
        Duration pause = Duration.ofMillis(400);
        stripHandler.execute(() -> {
            pulsate = true;

            while(pulsate){
                Color currentColor = color;
                int steps = 6;
                for (int i = 0; i < steps; i++) {
                    if(!pulsate){
                        return;
                    }
                    setColorToAllLeds(channel, currentColor);
                    sendColors(channel);
                    if(!pulsate){
                        return;
                    }
                    delay(pause);
                    currentColor = darker(currentColor);
                }
                for (int i = 0; i < steps; i++) {
                    if(!pulsate){
                        return;
                    }
                    setColorToAllLeds(channel, currentColor);
                    sendColors(channel);
                    if(!pulsate){
                        return;
                    }
                    delay(pause);
                    currentColor = lighter(currentColor);
                }
            }
        });
    }

    public void stopPulsation(){
        pulsate = false;
    }

    public void dim(Channel channel, Color originalColor, double percentage){
        if(Math.abs(percentages.get(channel) - percentage) < 0.15){
            return;
        }
        stripHandler.execute(() -> {
            double p = Math.min(percentage, 1.0);
            percentages.put(channel, p);

            int minRed   = Math.min(originalColor.getRed(),   5);
            int minGreen = Math.min(originalColor.getGreen(), 5);
            int minBlue  = Math.min(originalColor.getBlue(),  5);

            byte newRed   = (byte)Math.max(minRed,   (int)(originalColor.getRed()   * p));
            byte newGreen = (byte)Math.max(minGreen, (int)(originalColor.getGreen() * p));
            byte newBlue  = (byte)Math.max(minBlue,  (int)(originalColor.getBlue()  * p));

            byte[] colorBytes = this.colorBytes.get(channel);
            for (int i = 0; i < numberOfLeds(channel); i++) {
                colorBytes[i * BYTES_PER_PIXEL]       = newRed;
                colorBytes[(i * BYTES_PER_PIXEL) + 1] = newGreen;
                colorBytes[(i * BYTES_PER_PIXEL) + 2] = newBlue;
            }

            sendColors(channel);
        });
    }

    public void showPercentage(Channel channel, Color color, double percentage){
        if(Math.abs(percentages.get(channel) - percentage) < (1.0 / numberOfLeds(channel))){
            return;
        }
        stripHandler.execute(() -> {
            double p = Math.min(percentage, 1.0);
            percentages.put(channel, p);
            int numberOfLedsOn = (int) Math.round(numberOfLeds(channel) * p);
            for (int i = 0; i < numberOfLedsOn; i++) {
                setColor(channel, i, color);
            }
            for (int i = numberOfLedsOn; i < numberOfLeds(channel); i++) {
                setColor(channel, i, Color.black);
            }

            sendColors(channel);
        });
    }

    public void sendColorToSingleLed(Channel channel, int ledIdx, Color color) {
        stripHandler.execute(() -> {
            if(colorIsDifferent(channel, ledIdx, color)){
                setColor(channel, ledIdx, color);
                sendColors(channel);
            }
        });
    }

    public void sendColorOneByOne(Channel channel, Color color, Duration pause) {
        stripHandler.execute(() -> {
            byte[] colorBytes = this.colorBytes.get(channel);
            for (int i = 0; i < numberOfLeds(channel); i++) {
                byte red   = colorBytes[i * BYTES_PER_PIXEL];
                byte green = colorBytes[(i * BYTES_PER_PIXEL) + 1];
                byte blue  = colorBytes[(i * BYTES_PER_PIXEL) + 2];
                setColor(channel, i, color);
                sendColors(channel);
                delay(pause);
                setColor(colorBytes, i, red, green, blue);
            }
            sendColors(channel);
        });
    }

    @Override
    public void reset() {
        stopBouncing();
        stopPulsation();
        stripHandler.shutdownNow();
        colorBytes.keySet().forEach(channel -> {
            setColorToAllLeds(channel, Color.black);
            sendColors(channel);
        });
        super.reset();
    }

    private short numberOfLeds(Channel channel){
        return (short) (colorBytes.get(channel).length / BYTES_PER_PIXEL);
    }

    private boolean setColorToAllLeds(Channel channel, Color color){
        boolean somethingHasChanged = false;
        for (int i = 0; i < numberOfLeds(channel); i++) {
            if(colorIsDifferent(channel, i, color)){
                somethingHasChanged = true;
                setColor(channel, i, color);
            }
        }
        return somethingHasChanged;
    }

    private boolean colorIsDifferent(Channel channel, int idx, Color color) {
        byte[] colorBytes = this.colorBytes.get(channel);
        return colorBytes[idx * BYTES_PER_PIXEL]       != (byte) color.getRed()   ||
               colorBytes[(idx * BYTES_PER_PIXEL) + 1] != (byte) color.getGreen() ||
               colorBytes[(idx * BYTES_PER_PIXEL) + 2] != (byte) color.getBlue();
    }

    private void setColorSequence(Channel channel, int startIdx, int length, Color color) {
        byte[] colorBytes = this.colorBytes.get(channel);
        for (int i = 0; i < length; i++) {
            setColor(colorBytes, startIdx + i, color.getRed(), color.getGreen(), color.getBlue());
        }
    }

    private void setColor(Channel channel, int idx, Color color){
        byte[] colorBytes = this.colorBytes.get(channel);
        setColor(colorBytes, idx, color.getRed(), color.getGreen(), color.getBlue());
    }

    private void setColor(byte[] colorBytes, int idx, int red, int green, int blue){
        if(idx * BYTES_PER_PIXEL + 2 < colorBytes.length){
            colorBytes[idx * BYTES_PER_PIXEL]       = (byte) red;
            colorBytes[(idx * BYTES_PER_PIXEL) + 1] = (byte) green;
            colorBytes[(idx * BYTES_PER_PIXEL) + 2] = (byte) blue;
        }
    }

    private void sendColors(Channel channel) {
        byte channelNumber = channel.channel;
        logDebug("Sending colors on channel " + channelNumber);
        byte[] rgbPerPixel = colorBytes.get(channel);

        byte rIndex = 1;
        byte gIndex = 0;
        byte bIndex = 2;
        byte wIndex = 0;

        short leds = numberOfLeds(channel);

        CRC32 crc = new CRC32();
        crc.reset();
        ByteBuffer headerBuffer = initHeaderBuffer(10, channelNumber, CH_WS2812_DATA);
        headerBuffer.put(BYTES_PER_PIXEL);
        headerBuffer.put((byte) (rIndex | (gIndex << 2) | (bIndex << 4) | (wIndex << 6)));
        headerBuffer.putShort(leds);
        byte[] header = headerBuffer.array();

        crc.update(header);
        write(header);

        crc.update(rgbPerPixel);
        write(rgbPerPixel);

        writeCrc(crc);

        sendDrawAll();
    }

    private void sendDrawAll() {
        CRC32 crc = new CRC32();
        crc.reset();
        ByteBuffer buffer = initHeaderBuffer(6, (byte) 0xff, CH_DRAW_ALL);
        byte[] bytes = buffer.array();
        crc.update(bytes);
        write(bytes);
        writeCrc(crc);
    }

    private void writeCrc(CRC32 crc) {
        byte[] crcBytes = new byte[4];
        packInt(crcBytes, 0, (int) crc.getValue());
        write(crcBytes);
    }

    private void packInt(byte[] outgoing, int index, int val) {
        outgoing[index++] = (byte) (val & 0xFF);
        val = val >> 8;
        outgoing[index++] = (byte) (val & 0xFF);
        val = val >> 8;
        outgoing[index++] = (byte) (val & 0xFF);
        val = val >> 8;
        outgoing[index] = (byte) (val & 0xFF);
    }

    private ByteBuffer initHeaderBuffer(int size, byte channel, byte command) {
        ByteBuffer buffer = ByteBuffer.allocate(size);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        buffer.put((byte) 'U');
        buffer.put((byte) 'P');
        buffer.put((byte) 'X');
        buffer.put((byte) 'L');
        buffer.put(channel);
        buffer.put(command);

        return buffer;
    }


    private Color darker(Color color) {
        double factor = 0.5;
        int minRed   = Math.min(color.getRed(), 3);
        int minGreen = Math.min(color.getGreen(), 3);
        int minBlue  = Math.min(color.getBlue(), 3);
        return new Color(Math.max((int)(color.getRed()   * factor), minRed),
                         Math.max((int)(color.getGreen() * factor), minGreen),
                         Math.max((int)(color.getBlue()  * factor), minBlue));
    }

    private Color lighter(Color color) {
        double factor = 0.35;
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        int i = (int)(1.0/(1.0-factor));
        if ( r == 0 && g == 0 && b == 0) {
            return new Color(i, i, i);
        }
        if ( r > 0 && r < i ) {
            r = i;
        }
        if ( g > 0 && g < i ) {
            g = i;
        }
        if ( b > 0 && b < i ) {
            b = i;
        }

        return new Color(Math.min((int)(r/factor), 255),
                Math.min((int)(g/factor), 255),
                Math.min((int)(b/factor), 255));
    }

}