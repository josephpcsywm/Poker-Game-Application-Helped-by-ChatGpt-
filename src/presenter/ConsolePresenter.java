package presenter;
// ConsolePresenter.java
public class ConsolePresenter implements Presenter {
    private OutputBoundary outputBoundary;

    public ConsolePresenter(OutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void presentPlayerHand(String playerName, String hand) {
        outputBoundary.showPlayerHand(playerName, hand);
    }

    @Override
    public void presentCommunityCards(String communityCards) {
        outputBoundary.showCommunityCards(communityCards);
    }

    @Override
    public void presentWinner(String winner) {
        outputBoundary.showWinner(winner);
    }

    @Override
    public void presentMessage(String message) {
        outputBoundary.showMessage(message);
    }

    @Override
    public void presentPlayerTurn(String playerName) {
        outputBoundary.showMessage(playerName + ", it's your turn.");
    }

    @Override
    public void presentPlayerBet(String playerName, int betAmount, int currentPot) {
        outputBoundary.showMessage(playerName + " bets " + betAmount + " chips.");
        outputBoundary.showMessage("Current pot: " + currentPot + " chips.");
    }
}

