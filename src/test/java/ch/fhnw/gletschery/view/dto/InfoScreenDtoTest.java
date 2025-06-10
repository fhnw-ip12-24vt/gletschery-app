package ch.fhnw.gletschery.view.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ch.fhnw.gletschery.view.dto.InfoScreenDto;

public class InfoScreenDtoTest {

    @Test
    public void testConstructorAndGetters() {
        InfoScreenDto dto = new InfoScreenDto(
                "checkmark.png", "Continue", "Cancel", "EndGame",
                "FontName", 10, 20, "p1.png", "p2.png", "2001", "2002",
                "img1.png", "img2.png", "Glacier1", "Glacier2", "Interesting Fact", "bg.png", true
        );

        assertEquals("checkmark.png", dto.getCheckmarkIcon());
        assertEquals("Continue", dto.getForsetzen());
        assertEquals("Cancel", dto.getCancel());
        assertEquals("EndGame", dto.getEndGame());
        assertEquals("FontName", dto.getFont());
        assertEquals(10, dto.getPlayer1Points());
        assertEquals(20, dto.getPlayer2Points());
        assertEquals("p1.png", dto.getPlayer1Image());
        assertEquals("p2.png", dto.getPlayer2Image());
        assertEquals("2001", dto.getDate1());
        assertEquals("2002", dto.getDate2());
        assertEquals("img1.png", dto.getImage1());
        assertEquals("img2.png", dto.getImage2());
        assertEquals("Glacier1", dto.getName1());
        assertEquals("Glacier2", dto.getName2());
        assertEquals("Interesting Fact", dto.getFact());
        assertEquals("bg.png", dto.getBackground());
        assertTrue(dto.isPair());
    }

    @Test
    public void testSetters() {
        InfoScreenDto dto = new InfoScreenDto("", "", "", "", "", 0, 0, "", "", "", "", "", "", "", "", "", "", false);

        dto.setFont("Arial");

        assertEquals("Arial", dto.getFont());
    }
}
