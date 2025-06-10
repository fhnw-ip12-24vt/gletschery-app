package ch.fhnw.gletschery.view.screens;

import ch.fhnw.gletschery.view.dto.InfoScreenDto;
import ch.trick17.gui.Gui;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse {@code InfoScreen} stellt einen Informationsbildschirm dar,
 * der Details und Informationen anzeigt, die im {@link InfoScreenDto} enthalten sind.
 */
public class InfoScreen implements Screen {

    private final InfoScreenDto dto;

    /**
     * Konstruktor zum Erstellen eines neuen {@code InfoScreen}.
     *
     * @param dto Datenobjekt, das alle notwendigen Informationen für die Darstellung des Informationsbildschirms enthält
     */
    public InfoScreen(InfoScreenDto dto) {
        this.dto = dto;
    }

    @Override
    public void draw(Gui window) {

        //Background und Font
        window.drawImage(dto.getBackground(), 0, 0);
        window.loadFont(dto.getFont());
        window.setFontFamily("Inter");

        // Punkte und Avatare links oben
        window.setFontSize(38);
        window.drawString(String.valueOf(dto.getPlayer1Points()), 157, 100);
        window.drawImage(dto.getPlayer1Image(), 40, 40, 0.33);
        window.drawString(String.valueOf(dto.getPlayer2Points()), 1760, 100);
        window.drawImage(dto.getPlayer2Image(), 1810, 54, 0.33);

        // Wenn das richtige Paar gefunden wurde, werden Bilder, Name, Datum und Fakt des Gletschers angezeigt.
        if (dto.isPair()) {
            window.setColor(62, 152, 75);
            window.fillRect(300, 162, 579, 579);
            window.drawImage(dto.getImage1(), 318, 180);
            window.fillRect(1041, 162, 579, 579);
            window.drawImage(dto.getImage2(), 1059, 180);
            window.setFontSize(64);
            window.setColor(19, 54, 86);
            window.setBold(true);
            String gletscherTitleLine1 = dto.getName1();
            double nameWidth = window.stringWidth(gletscherTitleLine1);
            window.drawString(gletscherTitleLine1, (window.getWidth() / 2) - nameWidth / 2, 100);
            window.setFontSize(50);
            window.setBold(false);
            window.setFontSize(40);
            window.setColor(4, 10, 16);
            window.drawString(dto.getDate1(), 318, 150);
            window.drawString(dto.getDate2(), 1600 - window.stringWidth(dto.getDate2()), 150);
            String[] wrappedLines = splitMessage(dto.getFact());
            int lineHeight = 50;
            for (int i = 0; i < wrappedLines.length; i++) {
                String line = wrappedLines[i];
                double textWidth = window.stringWidth(line);
                window.drawString(line, (window.getWidth() / 2) - textWidth / 2, 820 + i * lineHeight);
            }

            // Wenn das falsche Paar gefunden wurde, werden Bilder, Name, Datum des Gletschers angezeigt.
        } else {
            window.setColor(152, 62, 62);
            window.fillRect(300, 182, 579, 579);
            window.drawImage(dto.getImage1(), 318, 200);
            window.fillRect(1041, 182, 579, 579);
            window.drawImage(dto.getImage2(), 1059, 200);
            window.setFontSize(50);
            window.setColor(19, 54, 86);
            window.setBold(true);
            String gletscherTitleLine1 = dto.getName1();
            String gletscherTitleLine2 = dto.getName2();
            double nameWidth = window.stringWidth(gletscherTitleLine1);
            double nameWidth2 = window.stringWidth(gletscherTitleLine2);

            window.drawString(dto.getName1(), 318 + ((543 - nameWidth) / 2), 165);
            window.drawString(dto.getName2(), 1059 + ((543 - nameWidth2) / 2), 165);
            window.setBold(false);
            window.drawString(dto.getDate1(), 310, 820);
            window.drawString(dto.getDate2(), 1600 - window.stringWidth(dto.getDate2()), 820);
        }

        // Spiel Enden Button und Spiel Forsetzen Button
        window.setFontSize(38);
        window.setBold(false);
        window.drawString(dto.getEndGame(), 114, 1020);
        double iconWitdh = window.stringWidth(dto.getEndGame());
        window.drawImage(dto.getCancel(), 40, 980);
        window.drawImage(dto.getCheckmarkIcon(), iconWitdh + 160, 980);
        window.drawString(dto.getForsetzen(), iconWitdh + 234, 1020);

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
