package test;

import entity.Card;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CardTest {

    @Test
    public void testCardCreation() {
        Card card = new Card("Ace", "Hearts");
        assertEquals("Hearts", card.getSuit());
        assertEquals("Ace", card.getRank());
    }

    @Test
    public void testCardToString() {
        Card card = new Card("King", "Spades");
        assertEquals("King of Spades", card.toString());
    }

    @Test
    public void testCardEquality() {
        Card card1 = new Card("Two", "Diamonds");
        Card card2 = new Card("Two", "Diamonds");
        Card card3 = new Card("Two", "Clubs");

        assertEquals(card1, card2);
        assertEquals(card1.hashCode(), card2.hashCode());

        assertEquals(card1, card1);
        assertEquals(card1.hashCode(), card1.hashCode());

        assertNotEquals(card1, card3); // Cards with different suits are not considered equal
        assertNotEquals(card1.hashCode(), card3.hashCode());
    }
}