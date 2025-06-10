package ch.fhnw.gletschery.view.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ch.fhnw.gletschery.view.dto.VictoryScreenDto;
import ch.fhnw.gletschery.model.Player;

public class VictoryScreenDtoTest {

    @Test
    public void testConstructorWithoutWinner() {
        Player p1 = new Player();
        Player p2 = new Player();

        VictoryScreenDto dto = new VictoryScreenDto(
                p1, p2, "Congrats", "Info", "Next", "End", "bg.png", "Arial",
                "Cancel", "Check", "en"
        );

        assertEquals("Congrats", dto.getGratulation());
        assertEquals("Info", dto.getTextInfo());
        assertEquals("Next", dto.getNextGame());
        assertEquals("End", dto.getEndGame());
        assertEquals("bg.png", dto.getBackground());
        assertEquals("Arial", dto.getFont());
        assertEquals("Cancel", dto.getCancel());
        assertEquals("Check", dto.getCheckMark());
        assertEquals("en", dto.getLanguage());
        assertSame(p1, dto.getPlayer1());
        assertSame(p2, dto.getPlayer2());
        assertNull(dto.getWinner());
    }

    @Test
    public void testConstructorWithWinner() {
        Player winner = new Player();
        Player p1 = new Player();
        Player p2 = new Player();

        VictoryScreenDto dto = new VictoryScreenDto(
                winner, p1, p2, "Gratulation", "Text Info", "Next Game", "End Game",
                "bg.jpg", "FontName", "CancelBtn", "CheckMarkIcon", "fr"
        );

        assertSame(winner, dto.getWinner());
        assertSame(p1, dto.getPlayer1());
        assertSame(p2, dto.getPlayer2());
        assertEquals("Gratulation", dto.getGratulation());
        assertEquals("Text Info", dto.getTextInfo());
        assertEquals("Next Game", dto.getNextGame());
        assertEquals("End Game", dto.getEndGame());
        assertEquals("bg.jpg", dto.getBackground());
        assertEquals("FontName", dto.getFont());
        assertEquals("CancelBtn", dto.getCancel());
        assertEquals("CheckMarkIcon", dto.getCheckMark());
        assertEquals("fr", dto.getLanguage());
    }

    @Test
    public void testSetters() {
        VictoryScreenDto dto = new VictoryScreenDto(
                new Player(), new Player(), "", "", "", "", "", "", "", "", ""
        );

        dto.setFont("F");
        dto.setLanguage("L");

        assertEquals("F", dto.getFont());
        assertEquals("L", dto.getLanguage());
    }
}