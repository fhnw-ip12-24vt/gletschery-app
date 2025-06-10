package ch.fhnw.gletschery.view.screens;

import ch.trick17.gui.Gui;

/**
 * Repräsentiert den Instruktionsbildschirm des Spiels.
 * Dieser Bildschirm zeigt Anleitungen und Erklärungen basierend auf der gewählten Sprache und einem Bildpfad an.
 */
public class InstructionScreen implements Screen{
    private final String language;
    private final String imagePath;

    /**
     * Konstruktor zum Erstellen eines neuen {@code InstructionScreen}.
     *
     * @param language Die Sprache, in der die Anweisungen angezeigt werden sollen.
     * @param imagePath Der Pfad zum anzuzeigenden Bild.
     */
    public InstructionScreen(String language, String imagePath) {
        this.language = language;
        this.imagePath = imagePath;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public void draw(Gui window) {
        window.drawImage(splitPath(language, imagePath),0,0);
    }

    public String splitPath(String language, String path){
        int extensionIndex = path.lastIndexOf(".");
        String beforeExtension = path.substring(0, extensionIndex);
        String extension = path.substring(extensionIndex);

        return beforeExtension + language.toUpperCase() + extension;
    }
}
