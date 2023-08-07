package usecase;

import entity.Card;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HandComparator {
    private static final String[] RANKS = {
            "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"
    };

    public int compareHighCards(List<Card> cards1, List<Card> cards2) {
        for (int i = 0; i < cards1.size(); i++) {
            int rankIndex1 = getIndex(RANKS, cards1.get(i).getRank());
            int rankIndex2 = getIndex(RANKS, cards2.get(i).getRank());

            if (rankIndex1 > rankIndex2) {
                return 1;
            } else if (rankIndex1 < rankIndex2) {
                return -1;
            }
        }
        return 0; // Both hands have the same high cards
    }

    private int getIndex(String[] ranks, String rank) {
        for (int i = 0; i < ranks.length; i++) {
            if (ranks[i].equals(rank)) {
                return i;
            }
        }
        return -1;
    }
}
