package test;

import entity.Card;
import entity.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandTest {
    private Hand hand;

    @BeforeEach
    public void setUp() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card("2", "Hearts"));
        cards.add(new Card("5", "Diamonds"));
        cards.add(new Card("Ace", "Spades"));
        hand = new Hand(cards);
    }

    @Test
    public void testGetCards() {
        List<Card> cards = hand.getCards();
        assertEquals(3, cards.size());
        assertEquals("2 of Hearts", cards.get(0).toString());
        assertEquals("5 of Diamonds", cards.get(1).toString());
        assertEquals("Ace of Spades", cards.get(2).toString());
    }

    @Test
    public void testToString() {
        assertEquals("2 of Hearts, 5 of Diamonds, Ace of Spades", hand.toString());
    }
}