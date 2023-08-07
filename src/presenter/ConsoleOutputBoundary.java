package presenter;

import java.util.Scanner;

// ConsoleOutputBoundary.java
public class ConsoleOutputBoundary implements OutputBoundary {
    private Scanner scanner;

    public ConsoleOutputBoundary() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void showPlayerHand(String playerName, String hand) {
        System.out.println(playerName + "'s hand: " + hand);
    }

    @Override
    public void showCommunityCards(String communityCards) {
        System.out.println("Community Cards: " + communityCards);
    }

    @Override
    public void showWinner(String winner) {
        System.out.println("Winner: " + winner);
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    public int takeAction(String playerName) {
        System.out.println(playerName + ", it's your turn.");
        System.out.println("Enter 1 to call, 2 to raise, or 3 to fold:");
        int choice = scanner.nextInt();
        while (choice < 1 || choice > 3) {
            System.out.println("Invalid choice. Enter 1 to call, 2 to raise, or 3 to fold:");
            choice = scanner.nextInt();
        }
        return choice;
    }

    public int takeRaiseAmount(String playerName, int minRaiseAmount, int maxRaiseAmount) {
        System.out.println(playerName + ", enter the amount to raise (between " + minRaiseAmount + " and " + maxRaiseAmount + "):");
        int amount = scanner.nextInt();
        while (amount < minRaiseAmount || amount > maxRaiseAmount) {
            System.out.println("Invalid amount. Enter the amount to raise (between " + minRaiseAmount + " and " + maxRaiseAmount + "):");
            amount = scanner.nextInt();
        }
        return amount;
    }
}