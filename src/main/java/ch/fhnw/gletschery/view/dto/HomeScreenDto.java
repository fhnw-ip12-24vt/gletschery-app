package ch.fhnw.gletschery.view.dto;

/**
 * Das {@code HomeScreenDto} ist ein Data Transfer Object (DTO),
 * das alle UI-relevanten Informationen für den Homescreen der Anwendung enthält.
 * <p>
 * Es umfasst Texte, Icons, Schriftarten, Bilder und weitere UI-Elemente,
 * die zur Darstellung des Startbildschirms notwendig sind.
 */
public class HomeScreenDto {

    private String font;
    private final String backgroundImage;
    private String language;
    private final String logo;
    private final String startButtonLabelText;
    private final String selectionSubmitLabelText;
    private final String creditsButtonLabelText;
    private final String selectionChangeLabelText;
    private final String checkIcon;
    private final String changeSelectionIcon;
    private final String instructionLabelText;
    private final String pointerIcon;
    private final String slideShow;

    public HomeScreenDto(String font, String backgroundImage,
                         String language, String logo, String startButtonLabelText,
                         String selectionSubmitLabelText, String slideShow, String creditsButtonLabelText,
                         String selectionChangeLabelText, String checkIcon,
                         String changeSelectionIcon, String instructionLabelText,
                         String pointerIcon) {
        this.font = font;
        this.backgroundImage = backgroundImage;
        this.language = language;
        this.logo = logo;
        this.startButtonLabelText = startButtonLabelText;
        this.selectionSubmitLabelText = selectionSubmitLabelText;
        this.slideShow = slideShow;
        this.creditsButtonLabelText = creditsButtonLabelText;
        this.selectionChangeLabelText = selectionChangeLabelText;
        this.checkIcon = checkIcon;
        this.changeSelectionIcon = changeSelectionIcon;
        this.instructionLabelText = instructionLabelText;
        this.pointerIcon = pointerIcon;

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

    public String getLogo() {
        return logo;
    }

    public String getStartButtonLabelText() {
        return startButtonLabelText;
    }

    public String getSelectionSubmitLabelText() {
        return selectionSubmitLabelText;
    }

    public String getSlideShow() {
        return slideShow;
    }

    public String getCreditsButtonLabelText() {
        return creditsButtonLabelText;
    }

    public String getSelectionChangeLabelText() {
        return selectionChangeLabelText;
    }

    public String getCheckIcon() {
        return checkIcon;
    }

    public String getChangeSelectionIcon() {
        return changeSelectionIcon;
    }

    public String getInstructionLabelText() {
        return instructionLabelText;
    }

    public String getLanguageLabel(String language) {
        return switch (language) {
            case "en" -> "Language";
            case "fr" -> "Langue";
            default -> "Sprache";
        };
    }

    public String getPointerIcon() {
        return pointerIcon;
    }
}
