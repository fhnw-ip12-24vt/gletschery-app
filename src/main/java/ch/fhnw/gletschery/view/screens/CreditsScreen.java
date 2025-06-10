package ch.fhnw.gletschery.view.screens;

import ch.fhnw.gletschery.utilities.EnvironmentVariables;
import ch.fhnw.gletschery.view.dto.CreditsScreenDto;
import ch.trick17.gui.Gui;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse {@code CreditsScreen} stellt den Abspannbildschirm des Spiels dar.
 * Sie zeigt Informationen über die Mitwirkenden und ermöglicht dem Benutzer, zur vorherigen Ansicht zurückzukehren.
 */
public class CreditsScreen implements Screen {
    private final CreditsScreenDto dto;
    private final String background;
    private final String cancelIcon;
    private final String font;

    /**
     * Konstruktor zur Initialisierung des {@code CreditsScreen} mit den erforderlichen Ressourcen.
     *
     * @param dto         Datenobjekt mit allen Informationen für die Anzeige der Credits
     * @param background  Pfad zum Hintergrundbild
     * @param cancelIcon  Pfad zum Icon für die Rückkehr zur vorherigen Ansicht
     * @param font        Pfad zur Schriftartdatei
     */
    public CreditsScreen(CreditsScreenDto dto, String background, String cancelIcon, String font) {
        this.dto = dto;
        this.background = background;
        this.cancelIcon = cancelIcon;
        this.font = font;
    }

    @Override
    public void draw(Gui window) {
        window.loadFont(font);
        window.setFontFamily(EnvironmentVariables.FONT_FAMILY);
        window.drawImage(background, 0, 0);
        window.setBold(true);
        window.setColor(19, 54, 86);
        window.setFontSize(64);
        window.drawString(dto.getTitle(), 854, 138);
        window.setBold(false);
        window.setColor(4, 10, 16);
        window.setFontSize(38);
        String[] wrappedLines = splitMessage(dto.getText());
        int lineHeight = 50;

        for (int i = 0; i < wrappedLines.length; i++) {
            String line = wrappedLines[i];
            double textWidth = window.stringWidth(line);
            window.drawString(line, (window.getWidth() / 2) - textWidth / 2, 260 + i * lineHeight);
        }

        //Epics
        window.setFontSize(26);
        window.drawString(dto.getePics(), 360 + 90 - window.stringWidth(dto.getePics()) / 2, 890);
        window.drawImage(dto.getQrCodeEPics(), 350, 650, 0.25);

        //swisseduc
        window.drawString(dto.getSwissEduc(), 860 + 90 - window.stringWidth(dto.getSwissEduc()) / 2, 890);
        window.drawImage(dto.getQrCodeSwissEduc(), 850, 650, 0.25);

        //glacierchange
        window.drawString(dto.getGlacierChange(), 1360 + 90 - window.stringWidth(dto.getGlacierChange()) / 2, 890);
        window.drawImage(dto.getQrCodeGlacierChange(), 1350, 650, 0.25);

        window.setColor(0, 0, 0);
        window.setFontSize(EnvironmentVariables.FONT_SIZE_BUTTONS);
        window.drawString(dto.getBackHome(), EnvironmentVariables.TEXT_BOTTOM_LEFT_X, EnvironmentVariables.TEXT_BOTTOM_LEFT_Y);
        window.drawImage(cancelIcon, EnvironmentVariables.ICON_BOTTOM_LEFT_X, EnvironmentVariables.ICON_BOTTOM_LEFT_Y);
    }

    // Zeilen teilen, um den Text richtig auf dem Bildschirm darzustellen
    private String[] splitMessage(String text) {
        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        for (String word : text.split(" ")) {
            if (currentLine.length() + word.length() + 1 > 70) {
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
