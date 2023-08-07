package entity;

// Deck.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (String suit : Card.SUITS) {
            for (String rank : Card.RANKS) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public List<Card> dealCards(int numCards) {
        if (numCards > cards.size()) {
            throw new IllegalArgumentException("Not enough cards in the deck to deal.");
        }

        List<Card> dealtCards = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            dealtCards.add(cards.remove(0));
        }

        return dealtCards;
    }

    public List<Card> getCards() {
        return cards;
    }
}