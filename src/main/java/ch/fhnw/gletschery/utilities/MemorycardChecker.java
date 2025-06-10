package ch.fhnw.gletschery.utilities;

import ch.fhnw.gletschery.model.GameState;

/**
 * Die Klasse MemorycardChecker übernimmt die Prüfung und Validierung von
 * Karten.
 * Sie prüft Karten-IDs auf Gültigkeit, erkennt unentdeckte Paare und überwacht
 * den Spielfortschritt.
 */
public class MemorycardChecker {

    private final GameState gameState;

    /**
     * Konstruktor, der das {@link GameState} Objekt referenziert.
     *
     * @param gameState Das GameState Objekt zur Verwaltung des Spielzustands.
     */
    public MemorycardChecker(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Prüft, ob die eingegebenen Karten-IDs gültig sind.
     *
     * @param ids Ein Array von zwei Karten-IDs.
     * @return {@code true}, wenn beide IDs dem erwarteten Muster entsprechen und
     *         genau zwei IDs angegeben sind, ansonsten {@code false}.
     */
    public boolean isValidCard(String[] ids) {
        for (String id : ids) {
            if (id == null || id.length() != 3) {
                return false;
            }
            char c1 = id.charAt(0);
            char c2 = id.charAt(1);
            char c3 = id.charAt(2);
            if (!Character.isDigit(c1) || !Character.isDigit(c2)) {
                return false;
            }
            if (c3 != 'A' && c3 != 'B') {
                return false;
            }
        }
        return true;
    }

    /**
     * Prüft, ob die Karten-IDs ein unentdecktes Paar darstellen.
     *
     * @param ids Ein Array von zwei Karten-IDs.
     * @return {@code true}, wenn die Karten-IDs ein unentdecktes Paar
     *         repräsentieren, ansonsten {@code false}.
     */
    public boolean isPair(String[] ids) {
        char last1 = ids[0].charAt(2);
        char last2 = ids[1].charAt(2);
        if (last1 == last2) {
            return false;
        }
        int id1 = numericId(ids[0]);
        int id2 = numericId(ids[1]);
        return id1 == id2;
    }

    /**
     * Prüft, ob das Paar bereits gefunden wurde.
     *
     * @param i Der Index des Paares.
     * @return {@code true}, wenn das Paar bereits gefunden wurde, ansonsten
     *         {@code false}.
     */
    public boolean alreadyFound(int i) {
        return gameState.getFoundPairs()[i];
    }

    /**
     * Prüft, ob noch ein nicht gefundenes Paar im Spiel vorhanden ist.
     *
     * @return {@code true}, wenn alle Paare gefunden wurden, ansonsten
     *         {@code false}.
     */
    public boolean islastPairFound() {
        for (boolean b : gameState.getFoundPairs()) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    /**
     * Extrahiert die numerische ID aus einer Karten-ID, indem die ersten beiden
     * Zeichen
     * (Ziffern) direkt in eine Ganzzahl umgewandelt werden.
     * <p>
     * Diese Methode ist effizienter als
     * {@code Integer.parseInt(id.substring(0, 2))}, da sie
     * auf die Erstellung von Zwischenobjekten verzichtet und mit
     * ASCII arbeitet.
     * <p>
     * Beispiel: Für die Karten-ID {@code "12B"} wird der Rückgabewert {@code 12}
     * sein.
     *
     * @param id Die Karten-ID im Format "NNX", wobei N eine Ziffer und X ein
     *           Buchstabe ist.
     * @return Die numerische ID als Ganzzahl.
     * @throws IllegalArgumentException Wenn die ID kürzer als 2 Zeichen ist oder
     *                                  keine Ziffern enthält.
     */
    private int numericId(String id) {
        if (id == null || id.length() < 2 || !Character.isDigit(id.charAt(0)) || !Character.isDigit(id.charAt(1))) {
            throw new IllegalArgumentException("Ungültige Karten-ID: " + id);
        }
        return (id.charAt(0) - '0') * 10 + (id.charAt(1) - '0');
    }
}
