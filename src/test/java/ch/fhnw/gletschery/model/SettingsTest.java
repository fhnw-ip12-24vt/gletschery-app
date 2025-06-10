package ch.fhnw.gletschery.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class SettingsTest {

    @Test
    public void testSetAndGetLanguages() {
        Settings settings = new Settings();
        List<String> langs = Arrays.asList("de", "en", "fr");
        settings.setLanguages(langs);
        assertEquals(langs, settings.getLanguages());
    }

    @Test
    public void testSetAndGetDefaultLanguage() {
        Settings settings = new Settings();
        settings.setDefaultLanguage("de");
        assertEquals("de", settings.getDefaultLanguage());
    }

    @Test
    public void testSetAndGetBackgroundImage() {
        Settings settings = new Settings();
        settings.setBackgroundImage("bg.png");
        assertEquals("bg.png", settings.getBackgroundImage());
    }

    @Test
    public void testSetAndGetInstructionImage() {
        Settings settings = new Settings();
        settings.setInstructionImage("instructions.png");
        assertEquals("instructions.png", settings.getInstructionImage());
    }

    @Test
    public void testSetAndGetIntroductionImage() {
        Settings settings = new Settings();
        settings.setIntroductionImage("intro.png");
        assertEquals("intro.png", settings.getIntroductionImage());
    }

    @Test
    public void testSetAndGetLogoImage() {
        Settings settings = new Settings();
        settings.setLogoImage("logo.png");
        assertEquals("logo.png", settings.getLogoImage());
    }

    @Test
    public void testSetAndGetCredits() {
        Settings settings = new Settings();
        Credits credits = new Credits();
        settings.setCredits(credits);
        assertSame(credits, settings.getCredits());
    }
}
