package usecase;

import entity.Card;
import entity.Hand;

import java.util.*;

public class HandEvaluator {
    private static final String[] RANKS = {
            "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"
    };

    // Constants for hand ranks
    private static final int STRAIGHT_FLUSH = 8;
    private static final int FOUR_OF_A_KIND = 7;
    private static final int FULL_HOUSE = 6;
    private static final int FLUSH = 5;
    private static final int STRAIGHT = 4;
    private static final int THREE_OF_A_KIND = 3;
    private static final int TWO_PAIR = 2;
    private static final int ONE_PAIR = 1;
    private static final int HIGH_CARD = 0;

    public int evaluateHand(Hand hand) {
        List<Card> cards = hand.getCards();
        cards.sort(Comparator.comparingInt(c -> getIndex(c.getRank())));

        if (isFlush(cards) && isStraight(cards)) {
            return STRAIGHT_FLUSH;
        } else if (isFourOfAKind(cards)) {
            return FOUR_OF_A_KIND;
        } else if (isFullHouse(cards)) {
            return FULL_HOUSE;
        } else if (isFlush(cards)) {
            return FLUSH;
        } else if (isStraight(cards)) {
            return STRAIGHT;
        } else if (isThreeOfAKind(cards)) {
            return THREE_OF_A_KIND;
        } else if (isTwoPair(cards)) {
            return TWO_PAIR;
        } else if (isOnePair(cards)) {
            return ONE_PAIR;
        } else {
            return HIGH_CARD;
        }
    }

    private boolean isFlush(List<Card> cards) {
        Set<String> suits = new HashSet<>();
        for (Card card : cards) {
            suits.add(card.getSuit());
        }
        return suits.size() == 1;
    }

    private boolean isStraight(List<Card> cards) {
        List<Integer> sortedRanks = new ArrayList<>();
        for (Card card : cards) {
            String rank = card.getRank();
            int rankValue = getIndex(rank);
            sortedRanks.add(rankValue);
        }
        Collections.sort(sortedRanks);

        // Check if the ranks form a sequence
        for (int i = 1; i < sortedRanks.size(); i++) {
            int currentRank = sortedRanks.get(i);
            int prevRank = sortedRanks.get(i - 1);
            if (currentRank != prevRank + 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isFourOfAKind(List<Card> cards) {
        Map<String, Integer> rankCounts = getRankCounts(cards);
        return rankCounts.containsValue(4);
    }

    private boolean isFullHouse(List<Card> cards) {
        Map<String, Integer> rankCounts = getRankCounts(cards);
        return rankCounts.containsValue(3) && rankCounts.containsValue(2);
    }

    private boolean isThreeOfAKind(List<Card> cards) {
        Map<String, Integer> rankCounts = getRankCounts(cards);
        return Collections.frequency(rankCounts.values(), 3) == 1;
    }

    private boolean isTwoPair(List<Card> cards) {
        Map<String, Integer> rankCounts = getRankCounts(cards);
        return Collections.frequency(rankCounts.values(), 2) == 2;
    }

    private boolean isOnePair(List<Card> cards) {
        Map<String, Integer> rankCounts = getRankCounts(cards);
        for (int count : rankCounts.values()) {
            if (count == 2) {
                return true;
            }
        }
        return false;
    }
    private int getIndex(String rank) {
        return Arrays.asList(RANKS).indexOf(rank);
    }

    private Map<String, Integer> getRankCounts(List<Card> cards) {
        Map<String, Integer> rankCounts = new HashMap<>();
        for (Card card : cards) {
            String rank = card.getRank();
            rankCounts.put(rank, rankCounts.getOrDefault(rank, 0) + 1);
        }
        return rankCounts;
    }
}
