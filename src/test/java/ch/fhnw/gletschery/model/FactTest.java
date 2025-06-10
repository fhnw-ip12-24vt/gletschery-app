package ch.fhnw.gletschery.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FactTest {

    @Test
    public void testSetAndGetDe() {
        Fact fact = new Fact();
        fact.setDe("Gletscher schmelzen schnell.");
        assertEquals("Gletscher schmelzen schnell.", fact.de());
    }

    @Test
    public void testSetAndGetEn() {
        Fact fact = new Fact();
        fact.setEn("Glaciers are melting rapidly.");
        assertEquals("Glaciers are melting rapidly.", fact.en());
    }

    @Test
    public void testSetAndGetFr() {
        Fact fact = new Fact();
        fact.setFr("Les glaciers fondent rapidement.");
        assertEquals("Les glaciers fondent rapidement.", fact.fr());
    }
}
