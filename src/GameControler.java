public class GameControler {

    Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player otherPlayer;
    private boolean isPlayer1 = true;

    public GameControler(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;

        //player 1 starts
        currentPlayer = player1;
        otherPlayer = player2;
    }

    //starts the game loop
    public void startGame() {
        System.out.println("Game starting!");

        while (!isGameOver()) {
            playTurn();
            switchTurns();

        }

        endGame();
    }
    //one full turn
    public void playTurn() {
        System.out.println("\n" + currentPlayer.getName() + "'s turn");

        //player attacks the other player
        currentPlayer.attack(otherPlayer);
        switchTurns();
        //show opponent board AFTER attack)
        if (isPlayer1) {
            this.board.printP1Screen();
        } else  {
            this.board.printP2Screen();
        }

    }

    //switches turns between players
    private void switchTurns() {
        Player temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;
        isPlayer1 = !isPlayer1;
    }

    //checks win condition
    private boolean isGameOver() {
        return otherPlayer.getTotalShipsLeft() == 0;
    }

    //ends the game
    public void endGame() {
        System.out.println("\nGAME OVER!");
        System.out.println(currentPlayer.getName() + " wins!");
    }



}
