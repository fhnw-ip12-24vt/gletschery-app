package ch.fhnw.gletschery.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

public class CardTest {

    @Test
    public void testInitialValuesAreNull() {
        Card card = new Card();
        assertNull(card.getId());
        assertNull(card.getImage());
        assertNull(card.getDate());
    }

    @Test
    public void testFieldAccessViaReflection() throws Exception {
        Card card = new Card();

        Field idField = Card.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(card, "01A");

        Field imageField = Card.class.getDeclaredField("image");
        imageField.setAccessible(true);
        imageField.set(card, "img01.png");

        Field dateField = Card.class.getDeclaredField("date");
        dateField.setAccessible(true);
        dateField.set(card, "2023-01-01");

        assertEquals("01A", card.getId());
        assertEquals("img01.png", card.getImage());
        assertEquals("2023-01-01", card.getDate());
    }
}
