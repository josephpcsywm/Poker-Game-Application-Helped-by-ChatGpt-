package entity;

import java.util.ArrayList;

// CommunityCard.java
public class CommunityCard {
    private Hand hand;

    public CommunityCard() {
        hand = new Hand(new ArrayList<>());
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}