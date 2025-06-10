package ch.fhnw.gletschery.view.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HomeScreenDtoTest {

    @Test
    public void testConstructorAndGetters() {
        HomeScreenDto dto = new HomeScreenDto(
                "Inter", "bg.png", "de", "logo.png", "Start",
                "Submit", "Slides", "Credits", "Change",
                "check.png", "change.png", "Instructions", "pointer.png"
        );

        assertEquals("Inter", dto.getFont());
        assertEquals("bg.png", dto.getBackgroundImage());
        assertEquals("de", dto.getLanguage());
        assertEquals("logo.png", dto.getLogo());
        assertEquals("Start", dto.getStartButtonLabelText());
        assertEquals("Submit", dto.getSelectionSubmitLabelText());
        assertEquals("Slides", dto.getSlideShow());
        assertEquals("Credits", dto.getCreditsButtonLabelText());
        assertEquals("Change", dto.getSelectionChangeLabelText());
        assertEquals("check.png", dto.getCheckIcon());
        assertEquals("change.png", dto.getChangeSelectionIcon());
        assertEquals("Instructions", dto.getInstructionLabelText());
        assertEquals("pointer.png", dto.getPointerIcon());
    }

    @Test
    public void testSetters() {
        HomeScreenDto dto = new HomeScreenDto("", "", "", "", "", "", "", "", "", "", "", "", "");

        dto.setFont("Font");
        dto.setLanguage("EN");

        assertEquals("Font", dto.getFont());
        assertEquals("EN", dto.getLanguage());

    }

    @Test
    public void testGetLanguageLabel() {
        HomeScreenDto dto = new HomeScreenDto("", "", "", "", "", "", "", "", "", "", "", "", "");

        assertEquals("Language", dto.getLanguageLabel("en"));
        assertEquals("Langue", dto.getLanguageLabel("fr"));
        assertEquals("Sprache", dto.getLanguageLabel("de"));
        assertEquals("Sprache", dto.getLanguageLabel("xx"));
    }
}
