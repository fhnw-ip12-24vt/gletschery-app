package ch.fhnw.gletschery.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ch.fhnw.gletschery.model.CardPair;
import ch.fhnw.gletschery.model.Fact;
import ch.fhnw.gletschery.model.Card;

public class CardPairTest {

    @Test
    public void testInitialCardArrayNotNull() {
        CardPair pair = new CardPair();
        assertNotNull(pair.getCards());
        assertEquals(2, pair.getCards().length);
    }

    @Test
    public void testSetAndGetPairId() {
        CardPair pair = new CardPair();
        pair.setPairId("00");
        assertEquals("00", pair.getPairId());
        assertEquals("00", pair.getId()); // getId() delegiert
    }

    @Test
    public void testSetAndGetName() {
        CardPair pair = new CardPair();
        pair.setName("Aletsch");
        assertEquals("Aletsch", pair.getName());
    }

    @Test
    public void testSetAndGetFact() {
        Fact fact = null; // Kein öffentlicher Konstruktor vorhanden, daher null testen erlaubt
        CardPair pair = new CardPair();
        pair.setFact(fact);
        assertNull(pair.getFact());
    }

    @Test
    public void testSetAndGetCards() {
        Card cardA = new Card();
        Card cardB = new Card();
        Card[] cards = new Card[] { cardA, cardB };

        CardPair pair = new CardPair();
        pair.setCards(cards);

        assertArrayEquals(cards, pair.getCards());
        assertSame(cardA, pair.getCardA());
        assertSame(cardB, pair.getCardB());
    }
}

