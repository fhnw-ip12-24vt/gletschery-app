package ch.fhnw.gletschery.model;

import java.util.Map;

/**
 * Die Klasse Player repräsentiert einen Spieler im Spiel.
 * Sie speichert den Namen des Spielers und seine Punktzahl.
 */
public class Player {
    Map<String, String> name;
    int points = 0;
    String icon;

    // /**
    // * Konstruktor für Player, initialisiert den Spielernamen und setzt die Punkte
    // auf 0.
    // *
    // * @param name Der Name des Spielers.
    // */
    public Player() {
        points = 0;
    }

    /**
     * Getter- und Setter-Methoden:
     * <ul>
     * <li>{@link #getPoints()} - Gibt die aktuelle Punktzahl des Spielers
     * zurück.</li>
     * <li>{@link #increasePoints()} - Erhöht die Punktzahl des Spielers um 1.</li>
     * <li>{@link #getName()} - Gibt den Namen des Spielers zurück.</li>
     * </ul>
     */
    public int getPoints() {
        return points;
    }

    public void increasePoints() {
        points++;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName(String language) {
        return name.get(language);
    }

    public Map<String, String> getName() {
        return name;
    }

    public void setName(String language, String name) {
        this.name.put(language, name);
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void reset(){
        this.points = 0;
    }

    public String getNameByLanguage(String language){
        return name.get(language);
    }
}
