// Interface Adapter: ConsolePokerGame.java
import entity.Card;
import entity.CommunityCard;
import entity.Hand;
import entity.Player;
import presenter.ConsoleOutputBoundary;
import presenter.ConsolePresenter;
import presenter.OutputBoundary;
import presenter.Presenter;
import usecase.PokerGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// Main.java
public class Main {
    public static void main(String[] args) {
        OutputBoundary consoleOutput = new ConsoleOutputBoundary();
        Presenter presenter = new ConsolePresenter(consoleOutput);
        PokerGame pokerGame = new PokerGame(presenter);

        // Create the players
        Player player1 = new Player("Player 1", 1000);
        Player player2 = new Player("Player 2", 1000);

        pokerGame.startGame(player1, player2);
    }
}


