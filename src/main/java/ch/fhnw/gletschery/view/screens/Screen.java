package ch.fhnw.gletschery.view.screens;

import ch.trick17.gui.Gui;

/**
 * Die abstrakte Klasse Screen stellt die Basis für verschiedene
 * Bildschirmansichten dar.
 * Jede Unterklasse muss die Methode {@link #draw(Gui)} (Window)}
 * implementieren, um Inhalte
 * im angegebenen Fenster anzuzeigen.
 * 
 * @author Maurice Meier
 * creationDate 11.12.2024
 * lastEdited 13.12.2024
 */
public interface Screen {
    /**
     * Zeichnet den Inhalt der Bildschirmansicht im angegebenen Fenster.
     * Diese Methode muss von jeder Unterklasse implementiert werden.
     *
     * @param window Das Fenster, in dem der Inhalt gezeichnet wird.
     */
    public abstract void draw(Gui window);
}
