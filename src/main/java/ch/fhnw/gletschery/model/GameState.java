package ch.fhnw.gletschery.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Die Klasse GameState verwaltet den Zustand des Spiels, einschließlich der
 * Spieler, des aktiven Spielers,
 * der Anzahl der zu findenden Paare und des aktuellen Bildschirms.
 */
public class GameState {
    private final Player player1;
    private final Player player2;
    private Player activePlayer;
    private String currentLanguage;
    private String[] qrResults;

    /** dieser Array markiert, welche Paare bereits gefunden wurden. */
    private boolean[] foundPairs;

    /**
     * Konstruktor für GameState, initialisiert die Spieler, den aktiven Spieler,
     * den Startbildschirm und die Paarinformationen.
     *
     */
    public GameState(int numberOfPairs, String language, List<Player> players) {
        this.currentLanguage = language;
        this.player1 = players.get(0);
        this.player2 = players.get(1);
        this.activePlayer = players.get(0);
        foundPairs = new boolean[numberOfPairs];
    }


    /**
     * Getter- und Setter-Methoden:
     * <ul>
     * <li>{@link #getFoundPairs()} - Gibt das Array der gefundenen Paare
     * zurück.</li>
     * <li>{@link #setPairFound(int)} - Markiert ein bestimmtes Paar als
     * gefunden.</li>
     * <li>{@link #getActivePlayer()} - Gibt den aktiven Spieler zurück.</li>
     * <li>{@link #setActivePlayer(Player)} - Ändert den aktiven Spieler.</li>
     * <li>{@link #getPlayer1()} - Gibt den ersten Spieler zurück.</li>
     * <li>{@link #getPlayer2()} - Gibt den zweiten Spieler zurück.</li>
     * <li>{@link #getWinner()} - Gibt den Spieler mit der Höchsten punktzahl
     * zurück</li>
     * </ul>
     */

    public boolean[] getFoundPairs() {
        return foundPairs;
    }

    public void setPairFound(int id) {
        foundPairs[id] = true;
    }

    public String[] getQrResults() {
        return qrResults;
    }

    public void setQrResults(String[] qrResults) {
        this.qrResults = qrResults;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Optional<Player> getWinner() {
        if (player1.getPoints() > player2.getPoints()) {
            return Optional.of(player1);
        } else if (player2.getPoints() > player1.getPoints()) {
            return Optional.of(player2);
        } else {
            return Optional.empty();
        }
    }

    public void setActivePlayer(Player player) {
        this.activePlayer = player;
    }

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(String language) {
        this.currentLanguage = language;
    }

    /**
     * Setzt das Spiel zurück. Der aktive Spieler wird auf den ersten Spieler
     * gesetzt,
     * und die gefundenen Paare werden zurückgesetzt.
     */
    public void reset() {
        activePlayer = player1;
        player1.reset();
        player2.reset();
        Arrays.fill(foundPairs, false);
    }
}
