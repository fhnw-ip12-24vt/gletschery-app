package ch.fhnw.gletschery.view.dto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChangeLanguageScreenDtoTest {

    @Test
    public void testConstructorAndGetters() {
        ChangeLanguageScreenDto dto = new ChangeLanguageScreenDto(
                "Inter", "bg.png", "de", "MenuText", "arrow.png",
                "de.png", "fr.png", "en.png", "Confirm", "Change", "Home",
                "check.png", "next.png","cancel.png"
        );

        assertEquals("Inter", dto.getFont());
        assertEquals("bg.png", dto.getBackgroundImage());
        assertEquals("de", dto.getLanguage());
        assertEquals("MenuText", dto.getChangeLanguageMenu());
        assertEquals("arrow.png", dto.getPointerUpIcon());
        assertEquals("de.png", dto.getGermanFlagIcon());
        assertEquals("fr.png", dto.getFrenchFlagIcon());
        assertEquals("en.png", dto.getEnglishFlagIcon());
        assertEquals("Confirm", dto.getConfirmSelection());
        assertEquals("Change", dto.getChangeSelection());
        assertEquals("Home", dto.getBackHome());
        assertEquals("check.png", dto.getCheckMarkIcon());
        assertEquals("next.png", dto.getNextPageIcon());
        assertEquals("cancel.png", dto.getCancelIcon());
    }

    @Test
    public void testSetters() {
        ChangeLanguageScreenDto dto = new ChangeLanguageScreenDto("", "", "", "", "",
                "", "", "", "", "", "", "", "", "");

        dto.setFont("Font");
        dto.setLanguage("EN");


        assertEquals("Font", dto.getFont());
        assertEquals("EN", dto.getLanguage());
    }
}