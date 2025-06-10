package ch.fhnw.gletschery.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ch.fhnw.gletschery.model.UIContent;

import java.util.HashMap;
import java.util.Map;

public class UIContentTest {

    @Test
    public void testSetAndGetStrings() {
        UIContent content = new UIContent();

        content.setStartGameFromMenu("Start");
        content.setConfirmSelection("Confirm");
        content.setChangeSelection("Change");
        content.setCurrentLanguage("en");
        content.setStartGame("Begin");
        content.setEndGame("End");
        content.setBackHome("Home");
        content.setShowCredits("Credits");
        content.setShowInstructions("Instructions");
        content.setContinueGame("Continue");
        content.setPressAnyKey("Press");
        content.setChangeLanguage("ChangeLang");
        content.setChangeLanguageMenu("LangMenu");
        content.setSlideShow("Slides");
        content.setScanString("ScanHere");

        assertEquals("Start", content.getStartGameFromMenu());
        assertEquals("Confirm", content.confirmSelection());
        assertEquals("Change", content.changeSelection());
        assertEquals("en", content.getCurrentLanguage());
        assertEquals("Begin", content.startGame());
        assertEquals("End", content.endGame());
        assertEquals("Home", content.getBackHome());
        assertEquals("Credits", content.getShowCredits());
        assertEquals("Instructions", content.getShowInstructions());
        assertEquals("Continue", content.getContinueGame());
        assertEquals("Press", content.pressAnyKey());
        assertEquals("ChangeLang", content.changeLanguage());
        assertEquals("LangMenu", content.getChangeLanguageMenu());
        assertEquals("Slides", content.getSlideShow());
        assertEquals("ScanHere", content.getScanString());
    }

    @Test
    public void testGameMessagesMap() {
        UIContent content = new UIContent();
        Map<String, String> messages = new HashMap<>();
        messages.put("key1", "message1");
        messages.put("key2", "message2");

        content.setGameMessages(messages);
        assertEquals(messages, content.gameMessages());
        assertEquals("message1", content.getGameMessage("key1"));
        assertEquals("message2", content.getGameMessage("key2"));
    }
}
