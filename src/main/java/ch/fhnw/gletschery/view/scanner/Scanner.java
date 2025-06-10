package ch.fhnw.gletschery.view.scanner;

import ch.fhnw.gletschery.controller.GameController;
import ch.fhnw.gletschery.utilities.EnvironmentVariables;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Die Klasse {@code Scanner} ist verantwortlich für das Einlesen von QR-Codes
 * über eine externe Bildquelle. Sie steuert den Erfassungsprozess über ein Bash-Skript,
 * liest regelmäßig ein Bild ein und analysiert es auf enthaltene QR-Codes.
 * Die Ergebnisse werden an den GameController übergeben.
 */
public class Scanner {

    private final GameController gameController;
    private Process process;
    private Thread updateThread;
    private volatile boolean running = false;

    /**
     * Konstruktor, der einen Scanner mit dem zugehörigen GameController initialisiert.
     *
     * @param controller die Instanz des GameControllers
     */
    public Scanner(GameController controller) {
        gameController = controller;
    }

    /**
     * Startet das externe Bash-Skript, welches Bilder aufnimmt.
     *
     * @throws IOException wenn der Prozess nicht gestartet werden kann
     */
    private void starCapturesProcess() throws IOException {
        ProcessBuilder builder = new ProcessBuilder(EnvironmentVariables.BASH_FILE_PATH);
        builder.redirectErrorStream(true);
        process = builder.start();
    }

    /**
     * Startet einen Hintergrund-Thread, der kontinuierlich nach QR-Codes scannt.
     */
    private void startScanThread() {
        running = true;
        updateThread = new Thread(() -> {
            while (running) {
                String[] input = scanForQRcodes();
                gameController.storeQROutput(input == null ? new String[]{} : input);
            }
        });
        updateThread.start();
    }

    public void start(){
       try{
           starCapturesProcess();
       }catch (IOException e){
           System.err.println("Prozess not starting: \n" + e.getMessage());
       }
        startScanThread();
        System.err.println("Prozess: " + updateThread.isAlive());
    }

    public void stop() {
        running = false;
        if (updateThread != null) {
            try {
                updateThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (process != null) {
            process.destroy();
        }
        File file = new File("/path/to/image.jpg");
        if (file.exists()) {
            boolean deleted = file.delete();
            if (!deleted) {
                System.err.println("Could not delete " + file.getAbsolutePath());
            }
        }
    }

    /**
     * Liest ein Bild von der vordefinierten Datei ein und versucht,
     * alle enthaltenen QR-Codes zu erkennen.
     *
     * @return ein Array mit den erkannten QR-Code-Texten, oder {@code null}, wenn keine gefunden wurden
     */
    private String[] scanForQRcodes() {
        QRCodeMultiReader multiReader = new QRCodeMultiReader();
        try (FileInputStream fis = new FileInputStream(EnvironmentVariables.IMAGE_FILE_PATH)) {
            BufferedImage image = ImageIO.read(fis);
            if (image == null) {
                return null;
            }
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            Result[] results = multiReader.decodeMultiple(binaryBitmap);
            String[] returnValues = new String[results.length];
            int i = 0;
            for (Result result : results) {
                returnValues[i++] = result.getText();
            }
            return returnValues;
        } catch (IOException | NotFoundException e) {
            System.err.println(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }
}
