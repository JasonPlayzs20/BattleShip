import java.util.Scanner;

public class MyProgram
{
    public static void main(String[] args) {
        Board board = new Board();
        board.printP2Screen();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        board.clear();
        System.out.println(board.getStatus(board.getP1PlayingBoard(),2,2));
    }
}

