package ch.fhnw.gletschery.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreditsLanguageTest {

    @Test
    public void testSetAndGetTitle() {
        CreditsLanguage cl = new CreditsLanguage();
        cl.setTitle("Credits Title");
        assertEquals("Credits Title", cl.getTitle());
    }

    @Test
    public void testSetAndGetText() {
        CreditsLanguage cl = new CreditsLanguage();
        cl.setText("Some text");
        assertEquals("Some text", cl.getText());
    }

    @Test
    public void testSetAndGetEPics() {
        CreditsLanguage cl = new CreditsLanguage();
        cl.setePics("ePics Data");
        assertEquals("ePics Data", cl.getePics());
    }

    @Test
    public void testSetAndGetSwissEduc() {
        CreditsLanguage cl = new CreditsLanguage();
        cl.setSwissEduc("Swiss Educ Link");
        assertEquals("Swiss Educ Link", cl.getSwissEduc());
    }

    @Test
    public void testSetAndGetGlacierChange() {
        CreditsLanguage cl = new CreditsLanguage();
        cl.setGlacierChange("Change Info");
        assertEquals("Change Info", cl.getGlacierChange());
    }
}
