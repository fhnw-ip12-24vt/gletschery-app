package ch.fhnw.gletschery.model;

public class CardPair {
    private String pairId;
    private String name;
    private Fact fact;
    private Card[] cards = new Card[2];

    public String getId(){
        return pairId;
    }

    public String getName(){
        return name;
    }

    public Fact getFact(){
        return fact;
    }

    public Card[] getCards(){
        return cards;
    }

    public Card getCardA(){
        return cards[0];
    }

    public Card getCardB(){
        return cards[1];
    }

    public String getPairId() {
        return pairId;
    }

    public void setPairId(String pairId) {
        this.pairId = pairId;
    }

    public void setName(String glacierName) {
        this.name = glacierName;
    }

    public void setFact(Fact fact) {
        this.fact = fact;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }
}


