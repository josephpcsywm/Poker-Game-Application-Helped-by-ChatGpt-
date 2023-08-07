package entity;

import java.util.Objects;

public class Card {
    private final String rank;
    private final String suit;

    public static final String[] RANKS = {
            "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"
    };

    public static final String[] SUITS = {
            "Hearts", "Diamonds", "Clubs", "Spades"
    };


    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(rank, card.rank) && Objects.equals(suit, card.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

