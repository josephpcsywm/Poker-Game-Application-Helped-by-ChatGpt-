package usecase;

import entity.*;
import presenter.ConsolePresenter;
import presenter.Presenter;
import usecase.HandComparator;
import usecase.HandEvaluator;

import java.util.ArrayList;
import java.util.List;

public class PokerGame {
    private int pot = 0;
    private CommunityCard communityCard;

    private enum Street {
        PREFLOP,
        FLOP,
        TURN,
        RIVER
    }

    private Street currentStreet = Street.PREFLOP;
    private static final String[] RANKS = {
            "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"
    };

    private final HandEvaluator handEvaluator;
    private final HandComparator handComparator;
    private final Presenter presenter;

    public PokerGame(Presenter presenter) {
        handEvaluator = new HandEvaluator();
        handComparator = new HandComparator();
        this.presenter = presenter;
        this.pot = 0;
    }


    public void dealStartingHands(Player player1, Player player2) {
        Deck deck = new Deck();
        deck.shuffle();

        Hand hand1 = new Hand(deck.dealCards(2));
        Hand hand2 = new Hand(deck.dealCards(2));

        player1.setHand(hand1);
        player2.setHand(hand2);

        presenter.presentPlayerHand(player1.getName(), hand1.toString());
        presenter.presentPlayerHand(player2.getName(), hand2.toString());
    }

    public void dealCommunityCards() {
        Deck deck = new Deck();
        deck.shuffle();

        switch (currentStreet) {
            case FLOP:
                List<Card> flopCards = deck.dealCards(3);
                presenter.presentCommunityCards(Hand.cardsToString(flopCards));
                break;

            case TURN:
                List<Card> turnCard = deck.dealCards(1);
                presenter.presentCommunityCards(turnCard.get(0).toString());
                break;

            case RIVER:
                List<Card> riverCard = deck.dealCards(1);
                presenter.presentCommunityCards(riverCard.get(0).toString());
                break;

            default:
                // For PREFLOP, we don't need to do anything as community cards are not dealt yet.
                break;
        }
    }

    public String determineWinner(Player player1, Player player2, int pot) {
        // Get the community cards
        List<Card> communityCards = communityCard.getHand().getCards();

        // Combine player's hole cards with community cards
        player1.getHand().addAll(communityCards);
        player2.getHand().addAll(communityCards);

        // Evaluate the best hand for each player
        HandEvaluator handEvaluator = new HandEvaluator();
        int player1Score = handEvaluator.evaluateHand(player1.getHand());
        int player2Score = handEvaluator.evaluateHand(player2.getHand());

        System.out.println(player1Score + " and " + player2Score);

        // Compare hand rankings to determine the winner
        if (player1Score > player2Score) {
            return "Player 1";
        } else if (player1Score < player2Score) {
            return "Player 2";
        } else {
            // If it's a tie, use the high card to break the tie
            HandComparator handComparator = new HandComparator();
            return handComparator.compareHighCards(player1.getHand().getCards(), player2.getHand().getCards()) > 0 ? "Player 1" : "Player 2";
        }
    }

    public void startGame (Player player1, Player player2){
            communityCard = new CommunityCard();
            currentStreet = Street.PREFLOP;
            dealStartingHands(player1, player2);

            // Initialize the pot
            int pot = 0;

            // Betting round 1 - Player 1 starts
            int betAmount = 0;
            boolean player1Folded = false;
            boolean player2Folded = false;

            // Player 1's turn
            List<Integer> potList = bettingRound(player1, player2, betAmount);
            int potForPlayer1 = potList.get(0);
            int potForPlayer2 = potList.get(1);
            if (potForPlayer1 == 0) {
                presenter.presentWinner(player2.getName());
                player2.incrementChips(potForPlayer2);
                return;
            }
            if (potForPlayer2 == 0) {
                presenter.presentWinner(player1.getName());
                player1.incrementChips(potForPlayer1);
                return;
            }

            // Move to the next street (round)
            currentStreet = Street.FLOP;

            // Betting round 2 - Community cards revealed (Flop)
            dealCommunityCards(); // Deal flop (3 cards)
            potList = bettingRound(player1, player2, potForPlayer1);
            potForPlayer1 = potList.get(0);
            potForPlayer2 = potList.get(1);
            if (potForPlayer1 == 0) {
                presenter.presentWinner(player2.getName());
                player2.incrementChips(potForPlayer2);
                return;
            }
            if (potForPlayer2 == 0) {
                presenter.presentWinner(player1.getName());
                player1.incrementChips(potForPlayer1);
                return;
            }

            // Move to the next street (round)
            currentStreet = Street.TURN;

            // Betting round 3 - Turn
            dealCommunityCards(); // Deal turn (1 card)
            potList = bettingRound(player1, player2, potForPlayer1);
            potForPlayer1 = potList.get(0);
            potForPlayer2 = potList.get(1);
            if (potForPlayer1 == 0) {
                presenter.presentWinner(player2.getName());
                player2.incrementChips(potForPlayer2);
                return;
            }
            if (potForPlayer2 == 0) {
                presenter.presentWinner(player1.getName());
                player1.incrementChips(potForPlayer1);
                return;
            }

            // Move to the next street (round)
            currentStreet = Street.RIVER;

            // Betting round 4 - River
            dealCommunityCards(); // Deal river (1 card)
            potList = bettingRound(player1, player2, potForPlayer1);
            potForPlayer1 = potList.get(0);
            potForPlayer2 = potList.get(1);
            if (potForPlayer1 == 0) {
                presenter.presentWinner(player2.getName());
                player2.incrementChips(potForPlayer2);
                return;
            }
            if (potForPlayer2 == 0) {
                presenter.presentWinner(player1.getName());
                player1.incrementChips(potForPlayer1);
                return;
            }

            // Determine the winner based on the hands and community cards
            String winner = determineWinner(player1, player2, potForPlayer1);
            presenter.presentWinner(winner);

            // Distribute the pot based on the winner
            if (winner.equals(player1.getName())) {
                player1.incrementChips(potForPlayer1);
            } else if (winner.equals(player2.getName())) {
                player2.incrementChips(potForPlayer2);
            } else {
                // It's a tie, split the pot
                int halfPot = potForPlayer1 / 2;
                player1.incrementChips(halfPot);
                player2.incrementChips(halfPot);
            }
        }


        public List<Integer> bettingRound (Player currentPlayer, Player otherPlayer,int currentBetAmount){
            int playerAction = currentPlayer.takeAction(currentBetAmount, true);
            List<Integer> potList = new ArrayList<>();

            if (playerAction == 0) {
                presenter.presentWinner(currentPlayer.getName() + " (Fold)");
                otherPlayer.incrementChips(pot);
                potList.add(0);
                potList.add(pot);
                return potList;
            }

            // Update the pot with the player's bet amount
            addToPot(playerAction);

            presenter.presentPlayerBet(currentPlayer.getName(), playerAction, pot);

            // Check if the other player folds
            int otherPlayerAction = otherPlayer.takeAction(playerAction, false);
            if (otherPlayerAction == 0) {
                presenter.presentWinner(otherPlayer.getName() + " (Fold)");
                currentPlayer.incrementChips(pot);
                potList.add(pot);
                potList.add(0);
                return potList;
            }

            // Update the pot with the other player's bet amount
            addToPot(otherPlayerAction);

            presenter.presentPlayerBet(otherPlayer.getName(), otherPlayerAction, pot);

            // Return the updated pot amounts for both players
            potList.add(pot);
            potList.add(pot);
            return potList;
        }

        public int getPot () {
            return pot;
        }

        public void addToPot (int amount){
            pot += amount;
        }

    }


