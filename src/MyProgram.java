import java.util.Scanner;

public class MyProgram
{
    public static void main(String[] args) {
        Board board = new Board();

        Player player1 = new Player("Player 1", board, 1);
        Player player2 = new Player("Player 2", board, 2);

        player1.placeShips(board);
        player2.placeShips(board);

        System.out.println("\nPlayer 1 Board:");
        board.printP1Screen();
        board.clear();
        System.out.println("\nPlayer 2 Board:");
        board.printP2Screen();
    }
}

