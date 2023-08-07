package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Player;
import presenter.Presenter;
import usecase.PokerGame;

public class PokerGameTest {
    private PokerGame pokerGame;
    private TestPresenter testPresenter;

    @BeforeEach
    public void setUp() {
        testPresenter = new TestPresenter();
        pokerGame = new PokerGame(testPresenter);
    }

    @Test
    public void testStartGame_Player1Wins() {
        Player player1 = new Player("Player 1", 1000);
        Player player2 = new Player("Player 2", 1000);

        // Set player actions for testing
        testPresenter.setPlayerActions(new int[]{200, 300, 0, 0});

        pokerGame.startGame(player1, player2);

        // Assert the winner and chips
        assertEquals("Player 1", testPresenter.getLastWinner());
        assertEquals(1500, player1.getChips());
        assertEquals(500, player2.getChips());
    }

    @Test
    public void testStartGame_Player2Wins() {
        Player player1 = new Player("Player 1", 1000);
        Player player2 = new Player("Player 2", 1000);

        // Set player actions for testing
        testPresenter.setPlayerActions(new int[]{200, 0, 300, 0});

        pokerGame.startGame(player1, player2);

        // Assert the winner and chips
        assertEquals("Player 2", testPresenter.getLastWinner());
        assertEquals(500, player1.getChips());
        assertEquals(1500, player2.getChips());
    }

    @Test
    public void testStartGame_Tie() {
        Player player1 = new Player("Player 1", 1000);
        Player player2 = new Player("Player 2", 1000);

        // Set player actions for testing
        testPresenter.setPlayerActions(new int[]{200, 300, 0, 200});

        pokerGame.startGame(player1, player2);

        // Assert the winner and chips
        assertEquals("It's a tie!", testPresenter.getLastWinner());
        assertEquals(1000, player1.getChips());
        assertEquals(1000, player2.getChips());
    }

    // Helper TestPresenter class for testing
    private static class TestPresenter implements Presenter {
        private int[] playerActions;
        private String lastWinner;

        public void setPlayerActions(int[] playerActions) {
            this.playerActions = playerActions;
        }

        public String getLastWinner() {
            return lastWinner;
        }

        @Override
        public void presentPlayerHand(String playerName, String hand) {
        }

        @Override
        public void presentCommunityCards(String communityCards) {
        }

        @Override
        public void presentPlayerBet(String playerName, int betAmount, int pot) {
        }

        @Override
        public void presentWinner(String winner) {
            lastWinner = winner;
        }

        @Override
        public void presentMessage(String message) {

        }

        @Override
        public void presentPlayerTurn(String playerName) {

        }
    }
}


