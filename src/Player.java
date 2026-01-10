import java.util.ArrayList;

public class Player {
    //instance variables (top)
    private String name;
    private Board board;
    private ArrayList<Ship> ships;
    final int id;

    //constructor
    public Player(String name, int boardsize) {
        ...
    }

    //REQUIRED methods
    public void attack(Player opponent) {
        ...
    }

    public Board getBoard() {
        return board;
    }


    public int getTotalShipsLeft() {
        ..
    }

    public String getName() {
        return name;
    }

}