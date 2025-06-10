package ch.fhnw.gletschery.view.screens;

import ch.fhnw.gletschery.utilities.EnvironmentVariables;
import ch.fhnw.gletschery.view.dto.HomeScreenDto;
import ch.trick17.gui.Gui;

/**
 * Die Klasse {@code HomeScreen} repräsentiert den Startbildschirm des Spiels.
 * Sie verwaltet die Anzeige der Startmenüoptionen und den aktuellen ausgewählten Menüpunkt.
 */
public class HomeScreen implements Screen {

    private final HomeScreenDto dto;
    private int currentMenuIndex = 0;

    /**
     * Konstruktor zum Erstellen eines neuen {@code HomeScreen}.
     *
     * @param dto Datenobjekt, das alle notwendigen Informationen für die Darstellung des Startbildschirms enthält
     */
    public HomeScreen(HomeScreenDto dto) {
        this.dto = dto;
    }

    @Override
    public void draw(Gui window) {
        window.loadFont(dto.getFont());
        window.setFontFamily(EnvironmentVariables.FONT_FAMILY);
        window.setFontSize(38);
        window.drawImage(dto.getBackgroundImage(), 0, 0);
        window.drawImage(dto.getLogo(), 319, 163);
        window.drawString(dto.getSelectionSubmitLabelText(), 130, 950); // Auswahl bestätigen
        window.drawString(dto.getSelectionChangeLabelText(), 130, 1020); // Auswahl ändern
        window.drawImage(dto.getCheckIcon(), 40, 910);
        window.drawImage(dto.getChangeSelectionIcon(), 40, 980);
        window.setFontSize(45);
        window.setBold(true);

        // Sprache
        window.drawString(dto.getLanguageLabel(dto.getLanguage()),
                (double) EnvironmentVariables.GAME_WINDOW_WIDTH / 2 - window.stringWidth(dto.getLanguageLabel(dto.getLanguage())) / 2,
                700); // Spielanleitung und Credits +65

        // Spielanleitung
        window.drawString(dto.getInstructionLabelText(),
                (double) EnvironmentVariables.GAME_WINDOW_WIDTH / 2 - window.stringWidth(dto.getInstructionLabelText()) / 2,
                765);

        // SlideShow
        window.drawString(dto.getSlideShow(), (double) EnvironmentVariables.GAME_WINDOW_WIDTH / 2 - window.stringWidth(dto.getSlideShow()) / 2, 830);

        // Credits
        window.drawString(dto.getCreditsButtonLabelText(), 880, 895);
        window.setFontSize(80);
        int pointerIconYPosition;
        int topPosition = 530;

        // Pointer
        if (currentMenuIndex == 0) {
            pointerIconYPosition = (int) (topPosition + (currentMenuIndex * EnvironmentVariables.UPPER_SPACE_BETWEEN_SELECTIONS_MAIN_MENU));
        } else {
            int firsJump = 60;
            pointerIconYPosition = (int) (topPosition + (currentMenuIndex * EnvironmentVariables.UPPER_SPACE_BETWEEN_SELECTIONS_MAIN_MENU) + firsJump);
        }
        window.drawImage(dto.getPointerIcon(),
                switch (dto.getLanguage()) {
                    case "en" -> 1200;
                    case "fr" -> 1200;
                    default -> 1200;
                },
                pointerIconYPosition); // -70 vo start

        window.setColor(19, 54, 86);

        // Start
        window.drawString(dto.getStartButtonLabelText(),
                switch (dto.getLanguage()) {
                    case "en" -> 835;
                    case "fr" -> 650;
                    default -> 835;
                },
                600);
    }

    public void changeMenuPointerPosition(int currentMenuIndex) {
        this.currentMenuIndex = currentMenuIndex;
    }
}
