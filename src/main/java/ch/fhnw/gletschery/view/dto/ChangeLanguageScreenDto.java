package ch.fhnw.gletschery.view.dto;

/**
 * Das {@code ChangeLanguageScreenDto} ist ein Data Transfer Object (DTO), das alle
 * benötigten Informationen für den Bildschirm zum Ändern der Sprache enthält.
 * <p>
 * Es wird verwendet, um UI-relevante Texte und Bildpfade zwischen Backend und GUI
 * zu übertragen, ohne zusätzliche Logik zu enthalten.
 * </p>
 */
public class ChangeLanguageScreenDto {

    private String font;
    private final String backgroundImage;
    private String language;
    private final String changeLanguageMenu;
    private final String pointerUpIcon;
    private final String germanFlagIcon;
    private final String frenchFlagIcon;
    private final String englishFlagIcon;
    private final String confirmSelection;
    private final String changeSelection;
    private final String backHome;
    private final String checkMarkIcon;
    private final String nextPageIcon;
    private final String cancelIcon;

    public ChangeLanguageScreenDto(String font,
                                   String backgroundImage,
                                   String language,
                                   String changeLanguageMenu,
                                   String pointerUpIcon,
                                   String germanFlagIcon,
                                   String frenchFlagIcon,
                                   String englishFlagIcon,
                                   String confirmSelection,
                                   String changeSelection,
                                   String backHome,
                                   String checkMarkIcon,
                                   String nextPageIcon,
                                   String cancelIcon) {
        this.font = font;
        this.backgroundImage = backgroundImage;
        this.language = language;
        this.changeLanguageMenu = changeLanguageMenu;
        this.pointerUpIcon = pointerUpIcon;
        this.germanFlagIcon = germanFlagIcon;
        this.frenchFlagIcon = frenchFlagIcon;
        this.englishFlagIcon = englishFlagIcon;
        this.confirmSelection = confirmSelection;
        this.changeSelection = changeSelection;
        this.backHome = backHome;
        this.checkMarkIcon = checkMarkIcon;
        this.nextPageIcon = nextPageIcon;
        this.cancelIcon = cancelIcon;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getChangeLanguageMenu() {
        return changeLanguageMenu;
    }

    public String getPointerUpIcon() {
        return pointerUpIcon;
    }

    public String getGermanFlagIcon() {
        return germanFlagIcon;
    }

    public String getFrenchFlagIcon() {
        return frenchFlagIcon;
    }

    public String getEnglishFlagIcon() {
        return englishFlagIcon;
    }

    public String getConfirmSelection() {
        return confirmSelection;
    }

    public String getChangeSelection() {
        return changeSelection;
    }

    public String getBackHome() {
        return backHome;
    }

    public String getCheckMarkIcon() {
        return checkMarkIcon;
    }

    public String getNextPageIcon() {
        return nextPageIcon;
    }

    public String getCancelIcon() {
        return cancelIcon;
    }

}
