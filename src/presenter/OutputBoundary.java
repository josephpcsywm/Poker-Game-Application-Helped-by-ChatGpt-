package presenter;
// OutputBoundary.java
public interface OutputBoundary {
    void showPlayerHand(String playerName, String hand);

    void showCommunityCards(String communityCards);

    void showWinner(String winner);

    void showMessage(String message);
}