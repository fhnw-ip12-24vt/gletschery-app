package ch.fhnw.gletschery.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Die Klasse GameData repräsentiert die vollständigen Spieldaten,
 * die zur Initialisierung und Steuerung des Spiels benötigt werden.
 * Sie wird typischerweise aus einer JSON-Datei geladen.
 */
public class GameData {
    @JsonProperty("players")
    private List<Player> players;

    @JsonProperty("cardPairs")
    private List<CardPair> cardPairs;

    @JsonProperty("settings")
    private Settings settings;

    @JsonProperty("ui")
    private UI ui;

    @JsonProperty("design")
    private Design design;

    public GameData() {
    }

    public List<Player> getPlayers() {
        return players;
    }

    public UI getUI() {
        return ui;
    }

    public List<CardPair> getCardPairs() {
        return cardPairs;
    }

    public Map<String, CardPair> getCardPairsAsMap() {
        Map<String, CardPair> cardPairMap = new HashMap<>();
        for (CardPair cP : cardPairs) {
            cardPairMap.put(cP.getId(), cP);
        }
        return cardPairMap;
    }

    public Card getCardById(String id) {
        CardPair cardPair = getCardPairByCardId(id);
        if (cardPair == null) {
            return null;
        }

        for (Card c : cardPair.getCards()) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public String getFactByLanguageAndCardId(String cardId, String language) {
        CardPair cardPair = this.getCardPairByCardId(cardId);
        if (cardPair == null) {
            return "Kein Kartenpaar gefunden"; // TODO: read this message from json
        }

        Fact fact = cardPair.getFact();
        return switch (language) {
            case "fr" -> fact.fr();
            case "en" -> fact.en();
            default -> fact.de();
        };
    }

    private int getPairId(String cardId) {
        if (cardId == null || cardId.length() != 3) {
            throw new IllegalArgumentException("Ungültige Karten-ID: " + cardId);
        }
        return Integer.parseInt(cardId.substring(0, cardId.length() - 1));
    }

    public CardPair getCardPairByCardId(String cardId) {
        try{
            return cardPairs.get(getPairId(cardId));
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    /**
     * Gibt alle einzelnen Karten der gespeicherten Kartenpaare zurück.
     *
     * @return Eine Liste aller {@link Card}-Objekte.
     */
    public List<Card> getAllCards() {
        List<Card> cards = new ArrayList<>();
        for (CardPair cP : this.cardPairs) {
            cards.addAll(Arrays.asList(cP.getCards()));
        }
        return cards;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setCardPairs(List<CardPair> cardPairs) {
        this.cardPairs = cardPairs;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public void setUI(UI ui) {
        this.ui = ui;
    }

    public Design getDesign() {
        return design;
    }

    public void setDesign(Design design) {
        this.design = design;
    }
}
