import java.util.Scanner;
import java.util.Random;

public class BattleShipGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        Player player1 = new Player("Player 1", board, 1);
        Player player2 = new Player("Player 2", board, 2);
        GameControler gC = new GameControler(board, player1, player2);

        // wait who did this, its so good :D - jason.
        printMenu();
        System.out.println("Press Enter to read the rules...");
        scanner.nextLine();

        board.clear();
        gC.title();

        printRules();
        System.out.println("Press Enter to start the grind...");
        scanner.nextLine();

        board.clear();




        gC.startSelectionPlayer1();


        board.clear();
        // board.clear();
        gC.title();
        System.out.println("PLAYER 1 FINISHED. PLAYER 2, PREPARE FOR DEPLOYMENT.");
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
        board.clear();

        gC.startSelectionPlayer2();
        board.clear();

        // board.clear();
        gC.title();
        System.out.println("ALL SYSTEMS GO. COMMENCE BATTLE!");
        gC.startGame();
    }
// yo eveything goes perfectly until it finsihes P2 first turn and then it freezes, how come?
//yeah check js spam enter and then itll get stuck// it works for me?

// like press enter until u are having the player 1 radar and ocean appear, enter wtv coords like A 6 and then press enter, and then itll go to p2 and it freezes for me.

// ill try 6. for me it look sgood, but p1 ocean not hainv any "O" and p2 radar dont have any "O" //now its saying there is a error for finding gC;√ fixed
// hA?????
//o sht my bad
    //huh, ill check it out.

    //alan i figured out why it freezes, you prob forgot to enter a row or column. if you just spam enter, it breaks
    //uh type a first, the enter, then type a number, then enter. :D
    // k let me try
    // really?
    //also we should get a gc type file lol instead of cloging the file
    // when i press A when i am on Player 1 radar and ocean it freezes after gogn to P2
    // ohhhh how do i type it? " A 5 2" ?
    // yeah true lmao ill make one rn
    public static void printMenu() {
        System.out.println("========================================");
        System.out.println("           BATTLESHIP: THE GRIND        ");
        System.out.println("========================================");
        System.out.println("          [1] START MISSION             ");
        System.out.println("          [2] EXIT                      ");
        System.out.println("========================================");
    }


    public static void printRules() {
        System.out.println("\n--- HOW TO PLAY ---");
        System.out.println("PLACEMENT PHASE:");
        System.out.println(" * Use W, A, S, D to move the ship on your board.");
        System.out.println(" * Press SPACE or F to rotate the ship.");
        System.out.println(" * Press ENTER on an empty line to secure the ship.");
        System.out.println("\nBATTLE PHASE:");
        System.out.println(" * Enter coordinates (e.g., 'A 5') to fire at the enemy.");
        System.out.println(" * Sink all 4 enemy vessels to claim victory.");
        System.out.println("-------------------\n");
    }


    public static String getRandomMove() {
        Random rand = new Random();
        char row = (char) ('a' + rand.nextInt(10));
        int col = rand.nextInt(10);
        return row + " " + col;
    }
}