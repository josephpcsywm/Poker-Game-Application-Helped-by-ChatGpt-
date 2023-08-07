package entity;

import java.util.List;

// Entity: Hand.java
public class Hand {
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static String cardsToString(List<Card> cards) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cards.size(); i++) {
            sb.append(cards.get(i).toString());
            if (i < cards.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addAll(List<Card> cardsToAdd) {
        cards.addAll(cardsToAdd);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.toString()).append(", ");
        }
        return sb.substring(0, sb.length() - 2); // Remove the last ", "
    }
}