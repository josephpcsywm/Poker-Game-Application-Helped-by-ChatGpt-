package test;

import entity.Card;
import entity.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeckTest {

    private Deck deck;

    @BeforeEach
    public void setUp() {
        deck = new Deck();
    }

    @Test
    public void testDeckInitialization() {
        assertEquals(52, deck.getCards().size());
    }

    @Test
    public void testShuffle() {
        // Store a copy of the initial order of cards
        List<Card> initialOrder = new ArrayList<>(deck.getCards());

        // Shuffle the deck
        deck.shuffle();

        // Ensure the order is now different
        List<Card> shuffledOrder = deck.getCards();
        boolean isDifferent = !initialOrder.equals(shuffledOrder);
        assertEquals(true, isDifferent);
    }

    @Test
    public void testDealCards() {
        int numCards = 5;
        List<Card> dealtCards = deck.dealCards(numCards);

        assertEquals(numCards, dealtCards.size());
        assertEquals(52 - numCards, deck.getCards().size());
    }

    @Test
    public void testDealMoreCardsThanAvailable() {
        int numCards = 60;

        assertThrows(IllegalArgumentException.class, () -> {
            deck.dealCards(numCards);
        });
    }
}
