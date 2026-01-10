import java.util.ArrayList;

public class Board {
    private static final int Size = 10;
    private ArrayList<ArrayList<Status>> p1PlayingBoard;
    private ArrayList<ArrayList<Status>> p2PlayingBoard;
    private ArrayList<ArrayList<Status>> p1ViewBoard;
    private ArrayList<ArrayList<Status>> p2ViewBoard;

    public Board() {
        p1PlayingBoard = new ArrayList<>();
        p2PlayingBoard = new ArrayList<>();
        p1ViewBoard = new ArrayList<>();
        p2ViewBoard = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            p1PlayingBoard.add(new ArrayList<>());
            p2PlayingBoard.add(new ArrayList<>());
            p1ViewBoard.add(new ArrayList<>());
            p2ViewBoard.add(new ArrayList<>());
            for (int j = 0; j < 10; j++) {
                p1PlayingBoard.get(i).add(Status.EMPTY);
                p2PlayingBoard.get(i).add(Status.EMPTY);
                p1ViewBoard.get(i).add(Status.EMPTY);
                p2ViewBoard.get(i).add(Status.EMPTY);
            }
        }
    }

    public boolean canPlace(Ship ship, Player player) {
        int length = ship.getLength();

        for (int i = 0; i < length; i++) {
            int shipX = ship.orientation == Orientation.HORIZONTAL ? ship.x + i : ship.x;
            int shipY = ship.orientation == Orientation.HORIZONTAL ? ship.y + i : ship.y;

            if (shipX < 0 || shipX >= Size || shipY < 0 || shipY >= Size) {
                return false;
            }
            if (player.id == 1) {
                if (getP1PlayingBoard().get(shipX).get(shipY) != Status.EMPTY) {
//                    throw new IllegalArgumentException("You cannot park your ship there.");
                    return false;
                }
            }
            if (player.id == 2) {
                if (getP2PlayingBoard().get(shipX).get(shipY) != Status.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public void place(Ship ship, Player player) {
        if (!canPlace(ship, player)) {
            throw new IllegalArgumentException("You cannot park your ship there.");
        }
        int length = ship.getLength();
        for (int i = 0; i < length; i++) {
            int shipX = ship.orientation == Orientation.HORIZONTAL ? ship.x + i : ship.x;
            int shipY = ship.orientation == Orientation.HORIZONTAL ? ship.y + i : ship.y;
            if (player.id == 1) {
                p1PlayingBoard.get(shipX).set(shipY, Status.BOAT);
            }
        }
    }

    public ArrayList<ArrayList<Status>> getP1PlayingBoard() {

        return p1PlayingBoard;
    }

    public ArrayList<ArrayList<Status>> getP2PlayingBoard() {
        return p2PlayingBoard;
    }

    public Status getStatus(ArrayList<ArrayList<Status>> e, int x, int y) {
        return e.get(x).get(y);
    }


    public void printP1Screen() {
        System.out.println("      Player 1's Radar");
//        System.out.println();
        for (ArrayList<Status> row : p1ViewBoard) {
            for (Status status : row) {
                Translation.printTranslated(status);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("      Player 1's Ocean");
//        System.out.println();
        for (ArrayList<Status> row : p1PlayingBoard) {
            for (Status status : row) {
                Translation.printTranslated(status);
            }
            System.out.println();
        }
    }

    public void printP2Screen() {
        System.out.println("      Player 2's Radar");
//        System.out.println();
        for (ArrayList<Status> row : p2ViewBoard) {
            for (Status status : row) {
                Translation.printTranslated(status);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("      Player 2's Ocean");
//        System.out.println();
        for (ArrayList<Status> row : p2PlayingBoard) {
            for (Status status : row) {
                Translation.printTranslated(status);
            }
            System.out.println();
        }
    }

    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
