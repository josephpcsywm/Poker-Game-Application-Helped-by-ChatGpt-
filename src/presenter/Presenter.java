package presenter;

public interface Presenter {
    void presentPlayerHand(String playerName, String hand);

    void presentCommunityCards(String communityCards);

    void presentWinner(String winner);

    void presentMessage(String message);
    void presentPlayerTurn(String playerName);

    void presentPlayerBet(String playerName, int betAmount, int currentPot);

}