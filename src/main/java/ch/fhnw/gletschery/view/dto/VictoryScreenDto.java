package ch.fhnw.gletschery.view.dto;

/**
 * Die Klasse {@code VictoryScreenDto} ist ein Data Transfer Object (DTO),
 * das alle relevanten Informationen für den Siegesbildschirm eines Spiels enthält.
 * Dazu gehören Informationen über die Spieler, den Gewinner, die Benutzeroberfläche und
 * Texte zur Anzeige am Ende des Spiels.
 */

import ch.fhnw.gletschery.model.Player;

public class VictoryScreenDto {
    private final String gratulation;
    private final String textInfo;
    private final String nextGame;
    private final String endGame;
    private final String background;
    private String font;
    private final String cancel;
    private final String checkMark;
    private String language;

    Player winner;
    Player player1;
    Player player2;

    public VictoryScreenDto(Player player1,
                            Player player2,
                            String gratulation,
                            String textInfo,
                            String nextGame,
                            String endGame,
                            String background,
                            String font,
                            String cancel,
                            String checkMark,
                            String language) {

        this.player1 = player1;
        this.player2 = player2;
        this.gratulation = gratulation;
        this.textInfo = textInfo;
        this.nextGame = nextGame;
        this.endGame = endGame;
        this.background = background;
        this.font = font;
        this.cancel = cancel;
        this.checkMark = checkMark;
        this.language = language;
    }

    public VictoryScreenDto(Player winner,
                            Player player1,
                            Player player2,
                            String gratulation,
                            String textInfo,
                            String nextGame,
                            String endGame,
                            String background,
                            String font,
                            String cancel,
                            String checkMark,
                            String language) {
        this.winner = winner;
        this.player1 = player1;
        this.player2 = player2;
        this.gratulation = gratulation;
        this.textInfo = textInfo;
        this.nextGame = nextGame;
        this.endGame = endGame;
        this.background = background;
        this.font = font;
        this.cancel = cancel;
        this.checkMark = checkMark;
        this.language = language;
    }

    public String getGratulation() {
        return gratulation;
    }

    public String getTextInfo() {
        return textInfo;
    }

    public String getNextGame() {
        return nextGame;
    }

    public String getEndGame() {
        return endGame;
    }

    public String getBackground() {
        return background;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getCancel() {
        return cancel;
    }

    public String getCheckMark() {
        return checkMark;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getWinner() {
        return winner;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
