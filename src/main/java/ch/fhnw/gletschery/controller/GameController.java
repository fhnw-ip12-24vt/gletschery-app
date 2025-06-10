package ch.fhnw.gletschery.controller;

import java.util.List;

import ch.fhnw.gletschery.model.GameData;
import ch.fhnw.gletschery.utilities.EnvironmentVariables;
import ch.fhnw.gletschery.utilities.JsonReader;
import ch.fhnw.gletschery.utilities.MemorycardChecker;
import ch.fhnw.gletschery.model.GameState;
import ch.fhnw.gletschery.view.GUI;
import ch.fhnw.gletschery.view.PUI;
import ch.fhnw.gletschery.view.screens.ChangeLanguageScreen;
import ch.fhnw.gletschery.view.screens.DiaShowScreen;
import ch.fhnw.gletschery.view.screens.HomeScreen;

/**
 * Die Klasse GameController steuert den Spielfluss.
 * Sie koordiniert die Spielphasen, überprüft die Aktionen der Spieler
 * und aktualisiert den Zustand {@link GameState} des Spiels und der
 * Benutzeroberfläche.
 */
public class GameController {
    private final GameState gameState;
    private final MemorycardChecker memoryCardChecker;
    private final GUI gui;
    private final PUI pui;
    private final List<String> languages;
    private long lastInteractionTime;


    public GameController() {
        GameData gameData = readData();
        languages = gameData.getSettings().getLanguages();
        gameState = new GameState(gameData.getCardPairs().size(),
                gameData.getSettings().getDefaultLanguage(), gameData.getPlayers());
        gui = new GUI(gameState, gameData);
        pui = new PUI(this);
        memoryCardChecker = new MemorycardChecker(gameState);
    }

    private GameData readData() {
        try {
            return JsonReader.read(EnvironmentVariables.JSON_PATH);
        } catch (Exception e) {
            throw new RuntimeException("Could not read Json: " + e.getMessage());
        }
    }

    /**
     * Startet das Spiel, setzt den Spielzustand zurück und beginnt die Spiellogik.
     */
    public void startGame() {
        gui.showGameLoopScreen(GameLoopState.DRAW_TWO.gamestep());
        gameState.reset();
    }

    /*
     * Öffnet die grafische Benutzeroberfläche und zeigt den Startbildschirm an.
     * Registriert zudem die Listener für die Benutzerinteraktionen
     * und startet den Aktualisierungsprozess der Oberfläche.
     */
    public void start() {
        gui.open();
        pui.registerListeners();
        setLastActionTime();
        run();
    }

    /*
     * Führt die Hauptschleife des Spiels aus, in der die Benutzeroberfläche
     * kontinuierlich aktualisiert wird, solange das Fenster geöffnet ist.
     */
    public void run() {
        while (gui.isWindowOpen()) {
            gui.refresh(EnvironmentVariables.GUI_REFRESH_TIME);
            checkPassedTime();
        }
    }

    /*
     * Setzt das Spiel nach dem Aufdecken eines Kartenpaares oder eines Fehlversuchs
     * fort.
     * Aktualisiert die Benutzeroberfläche und setzt den Spielzustand zurück.
     */
    public void continueGame() {
        gui.showGameLoopScreen(GameLoopState.DRAW_TWO.gamestep());
    }

    /*
     * Beendet das Spiel und gibt die Spielergebnisse aus.
     */
    public void gameOver() {
        gui.showVictoryScreen();
    }

    /*
     * Startet das Spiel, setzt den Spielzustand zurück und beginnt die Spiellogik.
     */
    public void endGame() {
        gui.showHomeScreen();
        gameState.reset();
    }

    public GUI getGUI() {
        return gui;
    }

    public void startLanguageScreen() {
        gui.showChangeLanguageScreen();
    }

    public void showDiaShowScreen() {
        gui.showDiaShowScreen();
    }

    public void showInstructionsOrInterduction(boolean isInterduction) {
        gui.showInstructionScreen(isInterduction);
    }

    public void openCredits() {
        gui.showCreditScreen();
    }

    /*
     * Ändert die aktuelle Sprache des Spiels.

     * @param language Der Sprachcode der gewünschten Sprache (z.B. "de", "en").
     */
    public void changeLanguage(String language) {
        if (languages.contains(language)) {
            gameState.setCurrentLanguage(language);
            gui.showChangeLanguageScreen();
        } else {
            System.err.println("Sprache nicht verfügbar");
        }
    }


    // Ändert die Position des Menüzeigers im Startbildschirm.

    public void changeMenuPointerPosition(int currentMenuIndex) {
        if (gui.getCurrentScreen() instanceof HomeScreen homeScreen) {
            homeScreen.changeMenuPointerPosition(currentMenuIndex);
        }
    }

    // Ändert die Position des Sprachzeigers im Sprachwahlbildschirm.
    public void changeLanguagePointerPosition(String currentLanguage) {
        if (gui.getCurrentScreen() instanceof ChangeLanguageScreen changeLanguageScreen) {
            changeLanguageScreen.changeLanguagePointerPosition(currentLanguage);
        }
    }

    /*
     * Setzt die Zeit der letzten Benutzerinteraktion auf die aktuelle Systemzeit.
     * Diese wird verwendet, um Inaktivität zu erkennen und ggf. automatische Aktionen auszuführen.
     */
    public void setLastActionTime() {
        lastInteractionTime = System.currentTimeMillis();
    }

    /*
     * Überprüft, wie viel Zeit seit der letzten Benutzerinteraktion vergangen ist.
     * Zeigt abhängig vom aktuellen Bildschirm bei Inaktivität automatisch die DiaShow an.
     */
    public void checkPassedTime() {
        long currentTime = System.currentTimeMillis();
        if (!(gui.getCurrentScreen() instanceof DiaShowScreen)) {
            if (gui.getCurrentScreen() instanceof HomeScreen) {
                if (currentTime - lastInteractionTime >= EnvironmentVariables.TIMEOUT_DURATION_HOMESCREEN) {
                    gui.showDiaShowScreen();
                }
            } else {
                if (currentTime - lastInteractionTime >= EnvironmentVariables.TIMEOUT_DURATION_ELSE) {
                    gameState.reset();
                    gui.showDiaShowScreen();
                }
            }
        }
    }

    /*
     * Reagiert auf eine Button-Aktion während der DiaShow.
     *
     * @param openDiaScreen Wenn false, wird der Startbildschirm angezeigt;
     *                      wenn true, bleibt die DiaShow sichtbar.
     */
    public void buttonAction(boolean openDiaScreen) {
        setLastActionTime();
        if (gui.getCurrentScreen() instanceof DiaShowScreen && !openDiaScreen) {
            gui.showHomeScreen();
        }
    }

    /*
     * Extrahiert die numerische ID aus einer Karten-ID.
     *
     * @param id Die Karten-ID als String.
     * @return Die numerische ID als Integer.
     */
    private int getPairIDfromCardID(String id) {
        if (id == null || id.length() < 2) {
            throw new IllegalArgumentException("Ungültige Karten-ID: " + id);
        }
        return (id.charAt(0) - '0') * 10 + (id.charAt(1) - '0');
    }

    //Speichert die gescannten QR-Code-IDs im Spielzustand.
    public void storeQROutput(String[] ids) {
        gameState.setQrResults(ids);
    }

    /*
     * Überprüft die angegebenen Karten-IDs und verarbeitet die Logik zur
     * Validierung,
     * Paarerkennung und Fortschritt des Spiels.
     * <p>
     * Die Methode überprüft, ob die Karten-IDs gültig sind, ob sie ein Paar
     * darstellen
     * und ob das Paar bereits gefunden wurde. Je nach Ergebnis werden entsprechende
     * Benutzeroberflächenanzeigen aufgerufen, um den Spielstatus anzuzeigen.
     * Wenn alle Paare gefunden wurden, wird das Spiel beendet.
     * </p>
     */
    public void checkCards() {
        String[] ids = gameState.getQrResults();

        switch (ids.length) {
            case 0:
                gui.showGameLoopScreen(GameLoopState.SCAN_ERROR.gamestep());
                return;
            case 1:
                gui.showGameLoopScreen(GameLoopState.NOT_ENOUGH_CARDS.gamestep());
                return;
            default:
                if (ids.length > 2) {
                    gui.showGameLoopScreen(GameLoopState.TOO_MANY_CARDS.gamestep());
                    return;
                }
        }

        if (memoryCardChecker.isValidCard(ids)) {
            if (memoryCardChecker.isPair(ids)) {
                if (!memoryCardChecker.alreadyFound(getPairIDfromCardID(ids[0]))) {
                    foundPair(ids);
                } else {
                    gui.showGameLoopScreen(GameLoopState.ALREADY_FOUND_PAIR.gamestep());
                }
            } else {
                foundNoPair(ids);
            }
        }
    }

    //Gibt an, ob das letzte verbleibende Kartenpaar bereits gefunden wurde
    public boolean islastPairFound() {
        return memoryCardChecker.islastPairFound();
    }

    /*
     * Verarbeitet den Fall, in dem ein Paar erfolgreich entdeckt wurde.
     *
     * @param ids Die ID der gefundenen Karte.
     */
    private void foundPair(String[] ids) {
        gameState.setPairFound(getPairIDfromCardID(ids[0]));
        gameState.getActivePlayer().increasePoints();
        gui.showInfoScreen(ids, true);
    }

    /**
     * Verarbeitet den Fall, in dem kein Paar gefunden wurde.
     */
    private void foundNoPair(String[] ids) {
        gui.showInfoScreen(ids, false);
        swapPlayer();
    }

    /**
     * Wechselt den aktiven Spieler nach einem Fehlversuch.
     */
    private void swapPlayer() {
        gameState.setActivePlayer(gameState.getActivePlayer() ==
                gameState.getPlayer1() ? gameState.getPlayer2() : gameState.getPlayer1());
    }

    public enum GameLoopState {
        DRAW_TWO(0),
        TOO_MANY_CARDS(1),
        NOT_ENOUGH_CARDS(2),
        ALREADY_FOUND_PAIR(3),
        SCAN_ERROR(4);
        private final int gamestep;

        GameLoopState(int gameStep) {
            gamestep = gameStep;
        }

        public int gamestep() {
            return gamestep;
        }
    }
}
