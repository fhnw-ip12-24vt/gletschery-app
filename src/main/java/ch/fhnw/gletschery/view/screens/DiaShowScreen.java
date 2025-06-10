package ch.fhnw.gletschery.view.screens;

import ch.fhnw.gletschery.model.CardPair;
import ch.fhnw.gletschery.utilities.EnvironmentVariables;
import ch.trick17.gui.Gui;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Die Klasse {@code DiaShowScreen} stellt einen Bildschirm dar, auf dem in regelmäßigen Abständen
 * Kartenpaare und dazugehörige Schaltflächenbeschriftungen angezeigt werden – ähnlich einer Diashow.
 * Sie wird vor dem Spielstart angezeigt und dient der Information oder Einstimmung auf das Thema.
 */
public class DiaShowScreen implements Screen {

    private final CardPair[] cardPairs;
    private final String[] startButtonLabelTexts;
    private final String font;
    private final String backgroundImagePath;
    private int currentButtonLabelIndex;
    private CardPair currentCardPair;
    private long lastImageChangeTime = 0;
    private long lastTextChangeTime = 0;

    /**
            * Konstruktor zum Erstellen eines neuen {@code DiaShowScreen}.
            *
            * @param cardPairs              Eine Liste von Kartenpaaren, die angezeigt werden sollen
     * @param startButtonLabelText   Texte für den Startknopf, die im Laufe der Zeit wechseln
     * @param font                   Pfad zur Schriftart, die verwendet werden soll
     * @param backgroundImagePath    Pfad zum Hintergrundbild
     */
    public DiaShowScreen(CardPair[] cardPairs, String[] startButtonLabelText, String font, String backgroundImagePath) {
        this.cardPairs = cardPairs;
        startButtonLabelTexts = startButtonLabelText;
        this.font = font;
        this.backgroundImagePath = backgroundImagePath;
        currentCardPair = getRandomCardPair();

        long currentTime = System.currentTimeMillis();
        lastImageChangeTime = currentTime;
        lastTextChangeTime = currentTime;
    }

    @Override
    public void draw(Gui window) {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastImageChangeTime >= EnvironmentVariables.DIASHOW_IMAGE_CHANGE_INTERVAL) {
            currentCardPair = getRandomCardPair();
            lastImageChangeTime = currentTime;
        }

        if (currentTime - lastTextChangeTime >= EnvironmentVariables.DIASHOW_TEXT_CHANGE_INTERVAL) {
            currentButtonLabelIndex = (currentButtonLabelIndex + 1) % startButtonLabelTexts.length;
            lastTextChangeTime = currentTime;
        }

        window.drawImage(backgroundImagePath, 0, 0);
        window.loadFont(font);
        window.drawImage(currentCardPair.getCardA().getImage(), 318, 180);
        window.drawImage(currentCardPair.getCardB().getImage(), 1037, 180);

        window.setFontSize(50);
        window.setBold(true);
        window.drawString(currentCardPair.getCardA().getDate(), 318, 150);
        window.drawString(currentCardPair.getCardB().getDate(), 1430, 150);

        double centerX = window.getWidth() / 2;
        double centerY = (window.getHeight() / 2) + 300;
        String labelText = getNextButtonLabelText();

        window.setFontSize(64);
        window.setBold(true);
        double textWidth = window.stringWidth(labelText);
        window.drawString(labelText, centerX - textWidth / 2, centerY);
    }

    private CardPair getRandomCardPair() {
        int index = ThreadLocalRandom.current().nextInt(cardPairs.length);
        return cardPairs[index];
    }

    public String getNextButtonLabelText() {
        return startButtonLabelTexts[currentButtonLabelIndex];
    }
}