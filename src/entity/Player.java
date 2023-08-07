package entity;
// Player.java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private String name;
    private Hand hand;
    private int chips;
    private int betAmount;

    public Player(String name, int initialChips) {
        this.name = name;
        this.chips = initialChips;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getChips() {
        return chips;
    }

    public void bet(int amount) {
        if (amount <= chips) {
            chips -= amount;
        } else {
            throw new IllegalArgumentException("Not enough chips to bet.");
        }
    }

    public void incrementChips(int amount) {
        chips += amount;
    }

    public int takeAction(int currentBetAmount, boolean isFirstAction) {
        Scanner scanner = new Scanner(System.in);
        int action = 0;

        while (action != 1 && action != 2 && action != 3) {
            System.out.println(name + ", it's your turn.");

            if (isFirstAction) {
                System.out.println("Current bet amount: " + currentBetAmount);
                System.out.println("1. Bet");
            } else {
                System.out.println("Current bet amount: " + currentBetAmount);
                System.out.println("1. Call");
                System.out.println("2. Raise");
                System.out.println("3. Fold");
            }

            action = scanner.nextInt();

            if (isFirstAction && action == 1) {
                // Player chose to bet
                System.out.println("Enter the amount you want to bet:");
                int betAmount = scanner.nextInt();
                return betAmount;
            } else if (!isFirstAction) {
                if (action == 1) {
                    // Player chose to call (pay the current bet amount)
                    return currentBetAmount;
                } else if (action == 2) {
                    // Player chose to raise
                    System.out.println("Enter the amount you want to raise:");
                    int raiseAmount = scanner.nextInt();
                    return currentBetAmount + raiseAmount;
                } else if (action == 3) {
                    // Player chose to fold
                    return 0; // 0 represents folding
                }
            }

            System.out.println("Invalid action. Please choose again.");
        }

        return 0; // This line should never be reached, but it's here to satisfy the compiler
    }

    public void resetBet() {
        betAmount = 0;
    }

}