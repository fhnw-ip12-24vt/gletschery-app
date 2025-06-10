package ch.fhnw.gletschery.view.screens;

import ch.fhnw.gletschery.utilities.EnvironmentVariables;
import ch.trick17.gui.Gui;
import java.util.ArrayList;
import java.util.List;

public class GameLoopScreen implements Screen {
    private final String activePlayerIcon;
    private final String message;
    private final String font;
    private final String backgroundImage;
    private final String cancelIcon;
    private final String backHomeLabel;
    private final String player1Points;
    private final String player2Points;
    private final String player1Image;
    private final String player2Image;
    private final String checkIcon;
    private final String scanString;

    public GameLoopScreen(String activePlayerIcon,
                          String message,
                          String font,
                          String backgroundImage,
                          String cancelIcon,
                          String backHomeLabel,
                          String checkIcon,
                          String scanString,
                          String player1Image,
                          String player2Image,
                          int player1Points,
                          int player2Points) {

        this.activePlayerIcon = activePlayerIcon;
        this.message = message;
        this.font = font;
        this.backgroundImage = backgroundImage;
        this.cancelIcon = cancelIcon;
        this.backHomeLabel = backHomeLabel;
        this.player1Image = player1Image;
        this.player2Image = player2Image;
        this.player1Points = String.valueOf(player1Points);
        this.player2Points = String.valueOf(player2Points);
        this.checkIcon = checkIcon;
        this.scanString = scanString;
    }

    @Override
    public void draw(Gui window) {
        window.loadFont(font);
        window.setFontSize(55);
        window.drawImage(backgroundImage, 0, 0);

        double centerX = window.getWidth() / 2;
        double centerY = (window.getHeight() / 2) + 80;

        String[] wrappedLines = splitMessage(message);
        int lineHeight = 70;
        int totalHeight = wrappedLines.length * lineHeight;
        int startY = (int) centerY - totalHeight / 2 + 50;

        for (int i = 0; i < wrappedLines.length; i++) {
            String line = wrappedLines[i];
            double textWidth = window.stringWidth(line);
            window.drawString(line, centerX - textWidth / 2, startY + i * lineHeight);
        }
        window.setFontSize(38);

        window.drawString(String.valueOf(player1Points), 157, 100);
        window.drawImage(player1Image, 40, 40, 0.33);

        window.drawString(String.valueOf(player2Points), 1760, 100);
        window.drawImage(player2Image, 1810, 54, 0.33);

        window.drawImageCentered(activePlayerIcon, window.getWidth() / 2, centerY - 300);

        window.setColor(0, 0, 0);
        window.setFontSize(EnvironmentVariables.FONT_SIZE_BUTTONS);
        window.drawString(backHomeLabel, EnvironmentVariables.TEXT_BOTTOM_LEFT_X, EnvironmentVariables.TEXT_BOTTOM_LEFT_Y);
        window.drawImage(cancelIcon, EnvironmentVariables.ICON_BOTTOM_LEFT_X, EnvironmentVariables.ICON_BOTTOM_LEFT_Y);
        double spaceBetweenButtons = EnvironmentVariables.TEXT_BOTTOM_LEFT_X + window.stringWidth(backHomeLabel) + 54;
        window.drawString(scanString, EnvironmentVariables.TEXT_BOTTOM_LEFT_X + spaceBetweenButtons, EnvironmentVariables.TEXT_BOTTOM_LEFT_Y);
        window.drawImage(checkIcon, EnvironmentVariables.ICON_BOTTOM_LEFT_X + spaceBetweenButtons, EnvironmentVariables.ICON_BOTTOM_LEFT_Y);
    }

    // Zeilen teilen, um den Text richtig auf dem Bildschirm darzustellen
    private String[] splitMessage(String text) {
        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        for (String word : text.split(" ")) {
            if (currentLine.length() + word.length() + 1 > 30) {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder();
            }
            if (!currentLine.isEmpty()) {
                currentLine.append(" ");
            }
            currentLine.append(word);
        }
        lines.add(currentLine.toString());
        return lines.toArray(new String[0]);
    }
}