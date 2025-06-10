package ch.fhnw.gletschery.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.*;

public class GameDataTest {

    @Test
    public void testGetAndSetPlayers() {
        GameData data = new GameData();
        List<Player> players = Arrays.asList(new Player(), new Player());
        data.setPlayers(players);
        assertEquals(players, data.getPlayers());
    }

    @Test
    public void testGetCardPairsAsMap() {
        CardPair pair = new CardPair();
        pair.setPairId("01");
        List<CardPair> pairs = List.of(pair);

        GameData data = new GameData();
        data.setCardPairs(pairs);

        Map<String, CardPair> map = data.getCardPairsAsMap();
        assertEquals(1, map.size());
        assertEquals(pair, map.get("01"));
    }

    @Test
    public void testGetCardByIdValid() {
        Card card = new Card();
        CardPair pair = new CardPair();
        card = setCardId(card, "00A");
        pair.setCards(new Card[]{card, new Card()});
        pair.setPairId("00");
        List<CardPair> pairs = List.of(pair);

        GameData data = new GameData();
        data.setCardPairs(pairs);

        Card result = data.getCardById("00A");
        assertNotNull(result);
        assertEquals("00A", result.getId());
    }

    @Test
    public void testGetCardByIdInvalid() {
        GameData data = new GameData();
        data.setCardPairs(Collections.emptyList());
        assertNull(data.getCardById("xx"));
    }

    @Test
    public void testGetFactByLanguageAndCardId() {
        Fact fact = new Fact();
        fact.setDe("DE-Fact");
        fact.setEn("EN-Fact");
        fact.setFr("FR-Fact");

        Card card = setCardId(new Card(), "00B");
        CardPair pair = new CardPair();
        pair.setCards(new Card[]{card, new Card()});
        pair.setPairId("00");
        pair.setFact(fact);

        GameData data = new GameData();
        data.setCardPairs(List.of(pair));

        assertEquals("EN-Fact", data.getFactByLanguageAndCardId("00B", "en"));
        assertEquals("FR-Fact", data.getFactByLanguageAndCardId("00B", "fr"));
        assertEquals("DE-Fact", data.getFactByLanguageAndCardId("00B", "de"));
    }

    @Test
    public void testGetCardPairByCardId() {
        Card card = setCardId(new Card(), "02A");
        CardPair pair = new CardPair();
        pair.setCards(new Card[]{card, new Card()});
        pair.setPairId("02");

        GameData data = new GameData();
        data.setCardPairs(Arrays.asList(new CardPair(), new CardPair(), pair));

        assertSame(pair, data.getCardPairByCardId("02A"));
    }

    @Test
    public void testGetAllCards() {
        Card card1 = setCardId(new Card(), "03A");
        Card card2 = setCardId(new Card(), "03B");
        CardPair pair = new CardPair();
        pair.setCards(new Card[]{card1, card2});
        pair.setPairId("03");

        GameData data = new GameData();
        data.setCardPairs(List.of(pair));

        List<Card> all = data.getAllCards();
        assertEquals(2, all.size());
        assertTrue(all.contains(card1));
        assertTrue(all.contains(card2));
    }

    private Card setCardId(Card card, String id) {
        try {
            var field = Card.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(card, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return card;
    }
}
