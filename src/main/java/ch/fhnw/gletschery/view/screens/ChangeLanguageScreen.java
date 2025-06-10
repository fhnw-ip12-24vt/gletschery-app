package ch.fhnw.gletschery.view.screens;

import ch.fhnw.gletschery.utilities.EnvironmentVariables;
import ch.fhnw.gletschery.view.dto.ChangeLanguageScreenDto;
import ch.trick17.gui.Gui;

/**
 * Die Klasse {@code ChangeLanguageScreen} stellt den Bildschirm zur Auswahl
 * der Sprache dar. Benutzer können zwischen verschiedenen Sprachen wählen,
 * wobei eine grafische Benutzeroberfläche verwendet wird.
 */
public class ChangeLanguageScreen implements Screen {

    private final ChangeLanguageScreenDto dto;
    private String currentLanguage;

    /**
     * Konstruktor, der den Sprachwechselbildschirm mit den notwendigen Daten initialisiert.
     *
     * @param dto Datenobjekt mit allen Informationen zur Anzeige
     */
    public ChangeLanguageScreen(ChangeLanguageScreenDto dto) {
        this.dto = dto;
        currentLanguage = dto.getLanguage();
    }

    public void changeLanguagePointerPosition(String currentLanguage) {
        this.currentLanguage = currentLanguage;
    }

    @Override
    public void draw(Gui window) {
        window.setFullScreen(true);
        window.loadFont(dto.getFont());
        window.setFontFamily(EnvironmentVariables.FONT_FAMILY);
        window.setFontSize(38);
        window.drawImage(dto.getBackgroundImage(), 0, 0);

        // Auswahl bestätigen
        window.drawString(dto.getConfirmSelection(), 130, 920);
        window.drawImage(dto.getCheckMarkIcon(), 40, 880);

        // Auswahl ändern
        window.drawString(dto.getChangeSelection(), 130, 970);
        window.drawImage(dto.getNextPageIcon(), 40, 930);

        // Zurück zum Hauptmenü
        window.drawString(dto.getBackHome(), 130, 1020);
        window.drawImage(dto.getCancelIcon(), 40, 980);

        // Title
        window.setBold(true);
        window.setFontSize(64);
        window.setColor(19, 54, 86);
        window.drawString(dto.getChangeLanguageMenu(), switch (dto.getLanguage()) {
            case "en" -> 700;
            case "fr" -> 650;
            default -> 688;
        }, 138);

        window.setBold(false);
        window.setFontSize(38);
        window.setBold(true);

        // German Flag
        window.drawImage(dto.getGermanFlagIcon(), 130, 415);
        window.drawString(("Deutsch"), 330 - window.stringWidth("Deutsch") / 2, 375);

        // British Flag
        window.drawImage(dto.getEnglishFlagIcon(), 763, 415);
        window.drawString("English", 963 - window.stringWidth("English") / 2, 375);

        // French Flag
        window.drawImage(dto.getFrenchFlagIcon(), 1396, 415);
        window.drawString("Français", 1596 - window.stringWidth("Français") / 2, 375);

        // Gletschy Pointer :D
        // x bih linki flagge = 297
        // x bih flagge ide mitti = 930
        // x bih rechti flagge = 1563
        window.drawImage(dto.getPointerUpIcon(),
                switch (currentLanguage) {
                    case "en" -> 930;
                    case "fr" -> 1563;
                    default -> 297;
                },
                675);
    }
}
