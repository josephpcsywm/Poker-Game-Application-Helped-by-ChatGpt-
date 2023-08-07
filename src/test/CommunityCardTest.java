package test;

import entity.Card;
import entity.CommunityCard;
import entity.Deck;
import entity.Hand;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommunityCardTest {

    @Test
    public void testGetHand() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card("Ace", "Hearts"));
        cards.add(new Card("King", "Spades"));
        cards.add(new Card("Queen", "Diamonds"));
        CommunityCard communityCard = new CommunityCard();
        communityCard.setHand(new Hand(cards));

        assertEquals(3, communityCard.getHand().getCards().size());
        assertTrue(communityCard.getHand().getCards().contains(new Card("Ace", "Hearts")));
        assertTrue(communityCard.getHand().getCards().contains(new Card("King", "Spades")));
        assertTrue(communityCard.getHand().getCards().contains(new Card("Queen", "Diamonds")));
    }

    @Test
    public void testEmptyCommunityCard() {
        CommunityCard communityCard = new CommunityCard();

        assertEquals(0, communityCard.getHand().getCards().size());
    }

    @Test
    public void testDealCommunityCards() {
        Deck deck = new Deck();
        deck.shuffle();
        List<Card> dealtCards = deck.dealCards(5);

        CommunityCard communityCard = new CommunityCard();
        communityCard.setHand(new Hand(dealtCards));

        assertEquals(5, communityCard.getHand().getCards().size());
    }

    @Test
    public void testDealPartialCommunityCards() {
        Deck deck = new Deck();
        deck.shuffle();
        List<Card> dealtCards = deck.dealCards(3);

        CommunityCard communityCard = new CommunityCard();
        communityCard.setHand(new Hand(dealtCards));

        assertEquals(3, communityCard.getHand().getCards().size());
    }
}