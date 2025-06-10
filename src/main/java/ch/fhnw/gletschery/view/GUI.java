package ch.fhnw.gletschery.view;

import ch.fhnw.gletschery.model.*;
import ch.fhnw.gletschery.utilities.EnvironmentVariables;
import ch.fhnw.gletschery.view.dto.CreditsScreenDto;
import ch.fhnw.gletschery.view.dto.HomeScreenDto;
import ch.fhnw.gletschery.view.dto.InfoScreenDto;
import ch.fhnw.gletschery.view.dto.VictoryScreenDto;
import ch.fhnw.gletschery.view.screens.*;
import ch.fhnw.gletschery.view.dto.*;
import ch.trick17.gui.Gui;
import ch.trick17.gui.impl.swing.Window;

/**
 * Die Klasse GUI stellt die Benutzeroberfläche der Anwendung "Gletschery" dar.
 * Sie verwaltet ein Fenster (Window), zeigt einen Bildschirm (Screen) an und
 * bietet Methoden zur Steuerung und Aktualisierung des Fensters.
 *
 * @author Maurice Meier
 * creationDate 11.12.2024
 * lastEdited 13.12.2024
 */
public class GUI {
    private final GameState gameState;
    private final GameData gameData;
    private final Gui window;
    private Screen currentScreen;

    /**
     * Konstruktor für die Klasse GUI.
     * Initialisiert das Fenster mit Titel "Gletschery" und einer Größe von 800x600
     * Pixel.
     * Setzt den Standardbildschirm auf {@link TestScreen}.
     */
    public GUI(GameState gameState, GameData gameData) {
        this.gameData = gameData;
        this.gameState = gameState;
        window = new Window(EnvironmentVariables.GAME_WINDOW_TITLE,
                EnvironmentVariables.GAME_WINDOW_WIDTH,
                EnvironmentVariables.GAME_WINDOW_HEIGHT);
        showHomeScreen();
    }

    /**
     * Prüft, ob das Fenster aktuell geöffnet ist.
     *
     * @return {@code true}, wenn das Fenster geöffnet ist, ansonsten {@code false}.
     */
    public boolean isWindowOpen() {
        return window.isOpen();
    }

    /**
     * Öffnet das Fenster der Benutzeroberfläche.
     */
    public void open() {
        window.open();
        window.setFullScreen(EnvironmentVariables.FULLSCREEN);
    }

    /**
     * Aktualisiert den Inhalt des Fensters und lädt die aktuelle Bildschirmansicht
     * neu.
     */
    public void refresh(int time) {
        window.refreshAndClear(time);
        currentScreen.draw(window);
    }

    /**
     * Zeigt den InfoScreen an, der Informationen zu zwei aufgedeckten Karten
     * darstellt.
     * <p>
     * Wenn ein korrektes Paar gefunden wurde, wird sofort aktualisiert.
     * Bei einem falschen Paar wird eine kurze Wartezeit eingefügt.
     *
     * @param ids    Die IDs der aufgedeckten Karten.
     * @param isPair {@code true}, wenn die Karten ein Paar bilden, ansonsten
     *               {@code false}.
     */
    public void showInfoScreen(String[] ids, boolean isPair) {
        String id1 = ids[0];
        String id2 = ids[1];
        String background = gameData.getSettings().getBackgroundImage();
        String font = gameData.getDesign().getFont();
        String image1 = gameData.getCardById(id1).getImage();
        String image2 = gameData.getCardById(id2).getImage();
        String date1 = gameData.getCardById(id1).getDate();
        String date2 = gameData.getCardById(id2).getDate();
        String fact = gameData.getFactByLanguageAndCardId(id1, gameState.getCurrentLanguage());
        String name1 = gameData.getCardPairByCardId(id1).getName();
        String name2 = gameData.getCardPairByCardId(id2).getName();
        int player1Points = gameState.getPlayer1().getPoints();
        String player1Image = gameState.getPlayer1().getIcon();
        int player2Points = gameState.getPlayer2().getPoints();
        String player2Image = gameState.getPlayer2().getIcon();
        String endGame = getEndGame(gameState.getCurrentLanguage());
        String cancel = gameData.getUI().getCancelIcon();
        String checkMarkIcon = gameData.getUI().getCheckMarkIcon();
        String countinueGame = getCountinueGame(gameState.getCurrentLanguage());

        InfoScreenDto dto = new InfoScreenDto(checkMarkIcon,
                countinueGame, cancel, endGame, font, player1Points,
                player2Points, player1Image, player2Image, date1,
                date2, image1, image2, name1, name2, fact, background,
                isPair);

        currentScreen = new InfoScreen(dto);
    }

    /**
     * * * Zeigt den Spielbildschirm (GameLoopScreen) an. * * @param gameStep Der *
     * aktuelle Spielschritt: * *
     * <ul>
     * * * *
     * <li>0 - Normale Spielanzeige</li> * *
     * <li>1 - zu viele Karten ausgewählt</li> * *
     * <li>2 - zu wenige Karten ausgewählt</li> * *
     * <li>3 - bereits gefundenes Paar erneut ausgewählt</li> * *
     * </ul>
     */
    public void showGameLoopScreen(int gameStep) {
        String message;
        switch (gameStep) {
            case 1:
                // Zu viele Karten
                message = getGameMessage(gameState.getCurrentLanguage(), GameMessage.TOO_MANY.getKey());
                break;
            case 2:
                // Zu wenige Karten
                message = getGameMessage(gameState.getCurrentLanguage(), GameMessage.NOT_ENOUGH.getKey());
                break;
            case 3:
                // Karten bereits gefunden
                message = getGameMessage(gameState.getCurrentLanguage(), GameMessage.ALREADY_FOUND.getKey());
                break;
            case 4:
                // Scanfehler
                message = getGameMessage(gameState.getCurrentLanguage(), GameMessage.SCAN_ERROR.getKey());
                break;
            default:
                // Normale Spielanweisung
                String playerName = gameState.getActivePlayer().getName(gameState.getCurrentLanguage());
                String instruction = getGameMessage(gameState.getCurrentLanguage(), GameMessage.TAKE_TWO.getKey());
                message = playerName + instruction;
                break;
        }

        String backHome;
        String scanString;

        switch (gameState.getCurrentLanguage()) {
            case "en":
                backHome = gameData.getUI().en().getBackHome();
                scanString = gameData.getUI().en().getScanString();
                break;
            case "fr":
                backHome = gameData.getUI().fr().getBackHome();
                scanString = gameData.getUI().fr().getScanString();
                break;
            default:
                backHome = gameData.getUI().de().getBackHome();
                scanString = gameData.getUI().de().getScanString();
        }

        currentScreen = new GameLoopScreen(
                gameState.getActivePlayer().getIcon(),
                message,
                gameData.getDesign().getFont(),
                gameData.getSettings().getBackgroundImage(),
                gameData.getUI().getCancelIcon(),
                backHome,
                gameData.getUI().getCheckMarkIcon(),
                scanString,
                gameState.getPlayer1().getIcon(),
                gameState.getPlayer2().getIcon(),
                gameState.getPlayer1().getPoints(),
                gameState.getPlayer2().getPoints()
        );
    }

    /**
     * Erstellt und zeigt den DiaShow-Bildschirm an.
     *
     * Dabei werden alle Kartenpaare aus den Spieldaten in ein Array kopiert und
     * für jede konfigurierte Sprache ein Start-Label-Text erzeugt.
     * Anschließend wird der aktuelle Bildschirm auf eine neue DiaShowScreen-Instanz gesetzt,
     * welche die Kartenpaare, die Starttexte, die Schriftart und das Hintergrundbild enthält.
     */
    public void showDiaShowScreen() {
        CardPair[] cardPairs = new CardPair[gameData.getCardPairs().size()];

        for (int i = 0; i < gameData.getCardPairs().size(); i++) {
            cardPairs[i] = gameData.getCardPairs().get(i);
        }

        String[] startLabelTexts = new String[gameData.getSettings().getLanguages().size()];

        for (int i = 0; i < gameData.getSettings().getLanguages().size(); i++) {
            startLabelTexts[i] = getPressAnyKeyLabel(gameData.getSettings().getLanguages().get(i));
        }


        currentScreen = new DiaShowScreen(
                cardPairs,
                startLabelTexts,
                gameData.getDesign().getFont(),
                gameData.getSettings().getBackgroundImage()
        );
    }

    /**
     * Zeigt den Startbildschirm (HomeScreen) an.
     */
    public void showHomeScreen() {
        currentScreen = new HomeScreen(new HomeScreenDto(
                gameData.getDesign().getFont(),
                gameData.getSettings().getBackgroundImage(),
                gameState.getCurrentLanguage(),
                gameData.getSettings().getLogoImage(),
                getStartGameFromMenu(gameState.getCurrentLanguage()),
                getConfirmSelection(gameState.getCurrentLanguage()),
                getSlideShow(gameState.getCurrentLanguage()),
                getShowCredits(gameState.getCurrentLanguage()),
                getChangeSelection(gameState.getCurrentLanguage()),
                gameData.getUI().getCheckMarkIcon(),
                gameData.getUI().getNextPageIcon(),
                getInstructionLabelText(gameState.getCurrentLanguage()),
                gameData.getUI().getPointerIcon()
        ));
    }

    /**
     * Zeigt den Sprachauswahl-Screen an.
     * Hier können Spieler bevor sie eine Runde spielen die Sprache auswählen.
     */

    public void showChangeLanguageScreen() {
        currentScreen = new ChangeLanguageScreen(new ChangeLanguageScreenDto(
                gameData.getDesign().getFont(),
                gameData.getSettings().getBackgroundImage(),
                gameState.getCurrentLanguage(),
                getChangeLanguageMenu(gameState.getCurrentLanguage()),
                gameData.getUI().getPointerUpIcon(),
                gameData.getUI().getGermanFlagIcon(),
                gameData.getUI().getFrenchFlagIcon(),
                gameData.getUI().getEnglishFlagIcon(),
                getConfirmSelection(gameState.getCurrentLanguage()),
                getChangeSelection(gameState.getCurrentLanguage()),
                getBackHome(gameState.getCurrentLanguage()),
                gameData.getUI().getCheckMarkIcon(),
                gameData.getUI().getNextPageIcon(),
                gameData.getUI().getCancelIcon()
        ));
    }

    /**
     * Zeigt den Victory-Screen an, wenn das Spiel beendet ist.
     * Der Spieler mit den meisten Punkten wird als Gewinner angezeigt.
     */
    public void showVictoryScreen() {
        Player player1 = gameState.getPlayer1();
        Player player2 = gameState.getPlayer2();
        String gratulation = getGameMessage(gameState.getCurrentLanguage(), GameMessage.GAME_FINISHED_1.getKey());
        String nextGame = getGameMessage(gameState.getCurrentLanguage(), GameMessage.GAME_FINISHED_4.getKey());
        String endGame = getEndGame(gameState.getCurrentLanguage());
        String background = gameData.getSettings().getBackgroundImage();
        String font = gameData.getDesign().getFont();
        String cancel = gameData.getUI().getCancelIcon();
        String checkMark = gameData.getUI().getCheckMarkIcon();


        VictoryScreenDto dto;
        if (gameState.getWinner().isPresent()) {
            Player winner = gameState.getWinner().get();
            String textWinner = getGameMessage(gameState.getCurrentLanguage(), GameMessage.GAME_FINISHED_2.getKey());
            dto = new VictoryScreenDto(winner,
                    player1, player2, gratulation, textWinner, nextGame,
                    endGame, background, font, cancel, checkMark,
                    gameState.getCurrentLanguage());
        } else {
            String textDraw = getGameMessage(gameState.getCurrentLanguage(), GameMessage.GAME_FINISHED_3.getKey());
            dto = new VictoryScreenDto(player1,
                    player2, gratulation, textDraw, nextGame, endGame,
                    background, font, cancel, checkMark,
                    gameState.getCurrentLanguage());
        }
        currentScreen = new VictoryScreen(dto);
    }

    /**
     * Zeigt den Victory-Screen an, wenn das Spiel beendet ist.
     * Der Spieler mit den meisten Punkten wird als Gewinner angezeigt.
     */
    public void showCreditScreen() {
        Credits credits = gameData.getSettings().getCredits();
        CreditsLanguage lcred;
        String backMessage;
        switch (gameState.getCurrentLanguage()) {
            case "fr":
                lcred = credits.getFr();
                backMessage = gameData.getUI().fr().getBackHome();
                break;
            case "en":
                lcred = credits.getEn();
                backMessage = gameData.getUI().en().getBackHome();
                break;
            default:
                lcred = credits.getDe();
                backMessage = gameData.getUI().de().getBackHome();
                break;
        }

        CreditsScreenDto dto = new CreditsScreenDto(
                lcred.getePics(), lcred.getGlacierChange(), lcred.getSwissEduc(),
                credits.getQrCodeEPics(), credits.getQrCodeGlacierChange(), credits.getQrCodeSwissEduc(),
                lcred.getText(), lcred.getTitle(), backMessage
        );
        currentScreen = new CreditsScreen(dto, gameData.getSettings().getBackgroundImage(),
                gameData.getUI().getCancelIcon(),
                gameData.getDesign().getFont());
    }

    //Zeigt den Instruktions- oder Einführungsbildschirm abhängig vom übergebenen Parameter an.
    public void showInstructionScreen(boolean isInterduction) {
        InstructionScreen instructionScreen;
        if (isInterduction) {
            instructionScreen = new InstructionScreen(gameState.getCurrentLanguage(), EnvironmentVariables.INTRODUCTION_IMAGE);
        } else {
            instructionScreen = new InstructionScreen(gameState.getCurrentLanguage(), EnvironmentVariables.INSTRUCTION_IMAGE);
        }
        currentScreen = instructionScreen;
    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }

    private String getEndGame(String language) {
        return switch (language) {
            case "fr" -> gameData.getUI().fr().endGame();

            case "en" -> gameData.getUI().en().endGame();

            default -> gameData.getUI().de().endGame();
        };
    }

    private String getGameMessage(String language, String gameMessageKey) {
        return switch (language) {
            case "fr" -> gameData.getUI().fr().getGameMessage(gameMessageKey);

            case "en" -> gameData.getUI().en().getGameMessage(gameMessageKey);

            default -> gameData.getUI().de().getGameMessage(gameMessageKey);
        };
    }

    private String getCountinueGame(String language) {
        switch (language) {
            case "fr":
                return gameData.getUI().fr().getContinueGame();
            case "en":
                return gameData.getUI().en().getContinueGame();
            default:
                return gameData.getUI().de().getContinueGame();
        }
    }

    private String getStartGameFromMenu(String language) {
        switch (language) {
            case "fr":
                return gameData.getUI().fr().getStartGameFromMenu();
            case "en":
                return gameData.getUI().en().getStartGameFromMenu();
            default:
                return gameData.getUI().de().getStartGameFromMenu();
        }
    }

    private String getConfirmSelection(String language) {
        switch (language) {
            case "fr":
                return gameData.getUI().fr().confirmSelection();
            case "en":
                return gameData.getUI().en().confirmSelection();
            default:
                return gameData.getUI().de().confirmSelection();
        }
    }

    private String getSlideShow(String language) {
        switch (language) {
            case "fr":
                return gameData.getUI().fr().getSlideShow();
            case "en":
                return gameData.getUI().en().getSlideShow();
            default:
                return gameData.getUI().de().getSlideShow();
        }
    }

    private String getChangeSelection(String language) {
        switch (language) {
            case "fr":
                return gameData.getUI().fr().changeSelection();
            case "en":
                return gameData.getUI().en().changeSelection();
            default:
                return gameData.getUI().de().changeSelection();
        }
    }

    private String getPressAnyKeyLabel(String language) {
        switch (language) {
            case "fr":
                return gameData.getUI().fr().pressAnyKey();
            case "en":
                return gameData.getUI().en().pressAnyKey();
            default:
                return gameData.getUI().de().pressAnyKey();
        }
    }

    private String getShowCredits(String language) {
        switch (language) {
            case "fr":
                return gameData.getUI().fr().getShowCredits();
            case "en":
                return gameData.getUI().en().getShowCredits();
            default:
                return gameData.getUI().en().getShowCredits();
        }
    }

    private String getInstructionLabelText(String language) {
        switch (language) {
            case "fr":
                return gameData.getUI().fr().getShowInstructions();
            case "en":
                return gameData.getUI().en().getShowInstructions();
            default:
                return gameData.getUI().de().getShowInstructions();
        }
    }


    private String getChangeLanguageMenu(String language) {
        switch (language) {
            case "fr":
                return gameData.getUI().fr().getChangeLanguageMenu();
            case "en":
                return gameData.getUI().en().getChangeLanguageMenu();
            default:
                return gameData.getUI().de().getChangeLanguageMenu();
        }
    }

    private String getBackHome(String language) {
        switch (language) {
            case "fr":
                return gameData.getUI().fr().getBackHome();
            case "en":
                return gameData.getUI().en().getBackHome();
            default:
                return gameData.getUI().de().getBackHome();
        }
    }

    public enum GameMessage {
        TAKE_TWO("takeTwo"),
        TOO_MANY("tooMany"),
        NOT_ENOUGH("notEnough"),
        ALREADY_FOUND("alreadyFound"),
        GAME_FINISHED_1("gameFinished1"),
        GAME_FINISHED_2("gameFinished2"),
        GAME_FINISHED_3("gameFinished3"),
        GAME_FINISHED_4("gameFinished4"),
        SCAN_ERROR("scanError");
        private final String key;

        GameMessage(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }
}