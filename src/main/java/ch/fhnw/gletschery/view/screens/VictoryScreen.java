package ch.fhnw.gletschery.view.screens;

import ch.fhnw.gletschery.model.Player;
import ch.fhnw.gletschery.utilities.EnvironmentVariables;
import ch.fhnw.gletschery.view.dto.VictoryScreenDto;
import ch.trick17.gui.Gui;

/**
 * Repräsentiert den Sieg-Bildschirm des Spiels.
 * Zeigt Informationen über den Gewinner, die Spieler sowie relevante Texte und Grafiken an.
 */
public class VictoryScreen implements Screen {
    private final Player winner;
    private final Player player1;
    private final Player player2;
    private final String gratulation;
    private final String text;
    private final String nextGame;
    private final String endGame;
    private final String background;
    private final String cancel;
    private final String checkMark;
    private final String font;
    private final String language;

    /**
     * Konstruktor, der ein {@code VictoryScreenDto} verwendet,
     * um alle benötigten Daten für den Sieg-Bildschirm zu initialisieren.
     *
     * @param dto Datenobjekt mit allen notwendigen Informationen für den Sieg-Bildschirm
     */
    public VictoryScreen(VictoryScreenDto dto) {
        winner = dto.getWinner();
        player1 = dto.getPlayer1();
        player2 = dto.getPlayer2();
        gratulation = dto.getGratulation();
        text = dto.getTextInfo();
        nextGame = dto.getNextGame();
        endGame = dto.getEndGame();
        background = dto.getBackground();
        cancel = dto.getCancel();
        checkMark = dto.getCheckMark();
        font = dto.getFont();
        language = dto.getLanguage();
    }

    @Override
    public void draw(Gui window) {
        window.loadFont(font);
        window.setFontFamily("Inter");
        window.drawImage(background, 0, 0);
        window.setColor(19, 54, 86);
        window.setFontSize(64);
        window.setBold(true);
        window.drawString(gratulation, EnvironmentVariables.GUI_CENTER_WIDTH - window.stringWidth(gratulation) / 2, 221);
        window.setBold(false);

        window.drawImageCentered(player1.getIcon(), 570 + window.stringWidth(player1.getNameByLanguage(language)) / 2, 415);
        window.drawImageCentered(player2.getIcon(), 1035 + window.stringWidth(player2.getNameByLanguage(language)) / 2, 425);
        window.setColor(19, 54, 86);
        window.setFontSize(64);
        window.drawString(player1.getNameByLanguage(language), 570, 644);
        window.drawString(player2.getNameByLanguage(language), 1035, 644);
        window.setBold(true);

        double points1Length = window.stringWidth(String.valueOf(player1.getPoints())) / 2;
        double points2Length = window.stringWidth(String.valueOf(player2.getPoints())) / 2;
        double scoreLength = window.stringWidth(":") / 2;
        double points1Position = window.stringWidth(player1.getNameByLanguage(language)) / 2 + 570;
        double points2Position = window.stringWidth(player2.getNameByLanguage(language)) / 2 + 1035;
        double scoreCenter = window.getWidth() / 2;

        window.drawString(String.valueOf(player1.getPoints()), points1Position - points1Length, 740);
        window.drawString(":", scoreCenter - scoreLength, 740);
        window.drawString(String.valueOf(player2.getPoints()), points2Position - points2Length, 740);
        window.setBold(false);

        window.setColor(0, 0, 0);
        window.setFontSize(40);
        window.drawString(text, EnvironmentVariables.GUI_CENTER_WIDTH - window.stringWidth(text) / 2, 843);

        // Player and points
        window.setFontSize(38);
        double spacing = 54;
        window.drawString(endGame, (window.getWidth() / 2) - spacing - window.stringWidth(endGame), 933);
        window.drawString(nextGame, (window.getWidth() / 2) + spacing + 74, 933);
        window.drawImage(cancel, (window.getWidth() / 2) - spacing - window.stringWidth(endGame) - 74, 893);
        window.drawImage(checkMark, (window.getWidth() / 2) + spacing, 893);


    }
}
