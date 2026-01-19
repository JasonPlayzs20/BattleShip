import java.util.Scanner;

public class MyProgram
{
    public static void main(String[] args) {
        Board board = new Board();

        Player player1 = new Player("Player 1", board, 1);
        Player player2 = new Player("Player 2", board, 2);

        // player1.placeShips(board);
        // player2.placeShips(board);

        GameControler GC = new GameControler(board, player1, player2);
        GC.startSelectionPlayer1();
        GC.startSelectionPlayer2();
//        System.out.println(board.getP2PlayingBoard());

        Scanner scanner = new Scanner(System.in);


        //SOLDIERS, WE MUST GRIND THE PROJECT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        System.out.println("\nPlayer 1 Board:");
        board.printP1Screen();
        scanner.nextLine();
        board.clear();
//        System.out.println(board.getP2PlayingBoard());

//        System.out.println("\nPlayer 2 Board:");
        board.printP2Screen();
    }
}

