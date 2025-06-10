package ch.fhnw.gletschery.view;

import ch.fhnw.gletschery.controller.GameController;
import ch.fhnw.gletschery.view.scanner.Scanner;
import ch.fhnw.gletschery.view.screens.*;
import com.pi4j.Pi4J;
import com.pi4j.catalog.components.LedStrip;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalInputConfig;
import com.pi4j.io.gpio.digital.PullResistance;
import java.awt.*;

/**
 * Die PUI-Klasse verwaltet die physische Benutzerschnittstelle,
 * insbesondere die Tasten-Events, LED-Steuerung und Menü-Navigation.
 */
public class PUI {
    private final GameController gameController;
    private final Scanner scanner;
    private final GUI gui;

    private static final MenuItem[] MENU_ITEMS = new MenuItem[5];
    private static final LanguageItem[] LANGUAGE_ITEMS = new LanguageItem[3];
    private int currentMenuIndex = 0;
    private int currentLanguageIndex = 0;
    final Context pi4j = Pi4J.newAutoContext();
    private boolean isInterductionScreen = true;
    private boolean wasDiashowSelected = false;


    /* INITIALIZE NEW BUTTONS */
    final DigitalInputConfig middleButtonConfig = ButtonConfig.MIDDLE.toConfig(pi4j);
    DigitalInput middleButton = pi4j.create(middleButtonConfig);
    final DigitalInputConfig leftButtonConfig = ButtonConfig.LEFT.toConfig(pi4j);
    DigitalInput leftButton = pi4j.create(leftButtonConfig);
    final DigitalInputConfig rightButtonConfig = ButtonConfig.RIGHT.toConfig(pi4j);
    DigitalInput rightButton = pi4j.create(rightButtonConfig);
    /* INITIALIZE NEW BUTTONS */

    //LED
    private LedStrip ledStrip;
    private static final LedStrip.Channel LED_CHANEL = LedStrip.Channel.C0;
    private static final int LED_COUNT = 102;



    public PUI(GameController gameController) {
        this.gameController = gameController;
        gui = this.gameController.getGUI();
        scanner = new Scanner(gameController);

        MENU_ITEMS[0] = MenuItem.START_BUTTON;
        MENU_ITEMS[1] = MenuItem.LANGUAGE_ITEM;
        MENU_ITEMS[2] = MenuItem.INSTRUCTIONS;
        MENU_ITEMS[3] = MenuItem.DIASHOW;
        MENU_ITEMS[4] = MenuItem.CREDITS;
        LANGUAGE_ITEMS[0] = LanguageItem.GERMAN;
        LANGUAGE_ITEMS[1] = LanguageItem.ENGLISH;
        LANGUAGE_ITEMS[2] = LanguageItem.FRENCH;

        this.ledStrip = new LedStrip(LedStrip.Channel.C0, LED_COUNT);
        this.ledStrip.sendColorToAllLeds(LED_CHANEL,Color.white);
    }

    /**
     * Registriert die Listener für die Tasten (Mitte, Links, Rechts).
     *
     * Diese Methode bindet die Event-Handler an die entsprechenden Hardware-Tasten.
     */
    public void registerListeners() {
        middleButton.addListener(event -> handleMiddleButtonPressed(middleButton.isHigh()));
        leftButton.addListener(event -> handleLeftButtonPressed(leftButton.isHigh()));
        rightButton.addListener(event -> handleRightButtonPressed(rightButton.isHigh()));
    }

    /**
     * Verarbeitet das Drücken des mittleren Buttons.
     * Führt abhängig vom aktuellen Bildschirm unterschiedliche Aktionen aus (z. B. Spiel starten, Spiel fortsetzen, Karten prüfen).
     *
     * @param pressedState true, wenn der Button gedrückt wurde.
     */
    private void handleMiddleButtonPressed(boolean pressedState) {
        if (pressedState) {
            if (wasDiashowSelected) {
                wasDiashowSelected = false;
                currentMenuIndex = 0;
            }
            if(this.gui.getCurrentScreen() instanceof InfoScreen){
                this.ledStrip.sendColorToAllLeds(LED_CHANEL,Color.white);
                if(gameController.islastPairFound()){
                    gameController.gameOver();
                } else {
                    gameController.continueGame();
                }
            } else if (gui.getCurrentScreen() instanceof HomeScreen) {
                homeScreenInteractions();
            } else if (gui.getCurrentScreen() instanceof InstructionScreen) {
                gameController.startGame();
            } else if (gui.getCurrentScreen() instanceof VictoryScreen) {
                gameController.startGame();

            } else if (gui.getCurrentScreen() instanceof GameLoopScreen) {
                gameController.checkCards();
            } else if (gui.getCurrentScreen() instanceof ChangeLanguageScreen) {
                gameController.changeLanguage(LANGUAGE_ITEMS[currentLanguageIndex].getLanguageString());
            }
            gameController.buttonAction(wasDiashowSelected);
        }
    }

    /*
     * Verarbeitet das Drücken des linken Buttons.
     * Führt ggf. einen Spielabbruch durch und kehrt zum Startbildschirm zurück.
     */
    private void handleLeftButtonPressed(boolean pressedState) {
        if (pressedState) {
            if (wasDiashowSelected) {
                wasDiashowSelected = false;
                currentMenuIndex = 0;
            }
            if(!(this.gui.getCurrentScreen() instanceof HomeScreen)){
                this.gameController.endGame();
                scanner.stop();
                currentMenuIndex = 0;
            }
            gameController.buttonAction(false);
        }
    }

    /*
     * Verarbeitet das Drücken des rechten Buttons.
     * Navigiert durch das Hauptmenü oder durch die Sprachoptionen – abhängig vom aktuellen Bildschirm.
     */
    private void handleRightButtonPressed(boolean pressedState) {
        if (pressedState) {
            if (wasDiashowSelected) {
                wasDiashowSelected = false;
                currentMenuIndex = 0;
            }
            if (gui.getCurrentScreen() instanceof HomeScreen) {
                currentMenuIndex = currentMenuIndex == 4 ? 0 : ++currentMenuIndex;
                gameController.changeMenuPointerPosition(currentMenuIndex);
                isInterductionScreen = true;
            }
            if (gui.getCurrentScreen() instanceof ChangeLanguageScreen) {
                currentLanguageIndex = currentLanguageIndex == 2 ? 0 : ++currentLanguageIndex;
                gameController.changeLanguagePointerPosition(LANGUAGE_ITEMS[currentLanguageIndex].languageString);
            }
            gameController.buttonAction(false);
        }
    }

    /*
     * Führt die im Startbildschirm ausgewählte Menüaktion aus.
     * Startet z.B. das Spiel, zeigt die Anleitung oder Credits, wechselt zur Sprachwahl oder startet die Diashow.
     */
    private void homeScreenInteractions() {
        switch (MENU_ITEMS[currentMenuIndex]) {
            case START_BUTTON:
                isInterductionScreen = true;
                scanner.start();
                gameController.showInstructionsOrInterduction(true);
                break;
            case LANGUAGE_ITEM:
                gameController.startLanguageScreen();
                break;
            case CREDITS:
                gameController.openCredits();
                break;
            case INSTRUCTIONS:
                isInterductionScreen = false;
                gameController.showInstructionsOrInterduction(false);
                break;
            case DIASHOW:
                wasDiashowSelected = true;
                gameController.showDiaShowScreen();
                break;
            default:
                break;
        }
    }

    enum MenuItem {
        START_BUTTON, LANGUAGE_ITEM, INSTRUCTIONS, DIASHOW, CREDITS
    }

    enum LanguageItem {
        GERMAN("de"), ENGLISH("en"), FRENCH("fr");
        private final String languageString;

        LanguageItem(String languageString) {
            this.languageString = languageString;
        }

        public String getLanguageString() {
            return languageString;
        }
    }

    enum ButtonConfig {
        LEFT("Left_Button", 22, 3000L),
        MIDDLE("Middle_Button", 24, 3000L),
        RIGHT("Right_Button", 23, 3000L);

        private final String name;
        private final int pin;
        private final long debounce;

        ButtonConfig(String name, int pin, long debounce) {
            this.name = name;
            this.pin = pin;
            this.debounce = debounce;
        }

        public String getName() {
            return name;
        }

        public int getPin() {
            return pin;
        }

        public String getId() {
            return "BCM_" + pin;
        }

        public long getDebounce() {
            return debounce;
        }

        public DigitalInputConfig toConfig(Context pi4j) {
            return DigitalInput.newConfigBuilder(pi4j)
                    .id(getId()).name(getName()).address(getPin())
                    .pull(PullResistance.PULL_DOWN)
                    .debounce(getDebounce()).build();
        }

    }
}
