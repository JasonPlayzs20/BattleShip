import java.util.ArrayList;

public class Board {
    private static final int Size = 10;
    private ArrayList<ArrayList<Status>> p1PlayingBoard;
    private ArrayList<ArrayList<Status>> p2PlayingBoard;

    public ArrayList<ArrayList<Status>> tempBoard;

    private ArrayList<ArrayList<Status>> p1ViewBoard;
    private ArrayList<ArrayList<Status>> p2ViewBoard;

    public Board() {
        this.p1PlayingBoard = new ArrayList<>();
        this.p2PlayingBoard = new ArrayList<>();
        this.p1ViewBoard = new ArrayList<>();
        this.p2ViewBoard = new ArrayList<>();
        this.tempBoard = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            this.p1PlayingBoard.add(new ArrayList<>());
            this.p2PlayingBoard.add(new ArrayList<>());
            this.p1ViewBoard.add(new ArrayList<>());
            this.p2ViewBoard.add(new ArrayList<>());
            this.tempBoard.add(new ArrayList<>());
            for (int j = 0; j < 10; j++) {
                this.p1PlayingBoard.get(i).add(Status.EMPTY);
                this.p2PlayingBoard.get(i).add(Status.EMPTY);
                this.p1ViewBoard.get(i).add(Status.EMPTY);
                this.p2ViewBoard.get(i).add(Status.EMPTY);
                this.tempBoard.get(i).add(Status.EMPTY);
            }
        }
    }

    public boolean canPlace(Ship ship, Player player) {
        int length = ship.getLength();

        for (int i = 0; i < length; i++) {
            int shipX = ship.orientation == Orientation.HORIZONTAL ? ship.x + i : ship.x;
            int shipY = ship.orientation == Orientation.HORIZONTAL ? ship.y : ship.y+i;

            if (shipX < 0 || shipX >= Size || shipY < 0 || shipY >= Size) {
                return false;
            }
            if (player.id == 1) {
//                System.out.println("Test:");
//                System.out.println("ith " + i);
//                System.out.println(shipY + " " + shipX);
//                System.out.println(printP1Screen());
//                printTempBoard();
//                System.out.println(getthis.p1PlayingBoard().get(shipY).get(shipX));
                if (getP1PlayingBoard().get(shipY).get(shipX) != Status.EMPTY) {
//                    throw new IllegalArgumentException("You cannot park your ship there.");
                    return false;
                }
            }
            if (player.id == 2) {
                if (getP2PlayingBoard().get(shipY).get(shipX) != Status.EMPTY) {
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
            int shipY = ship.orientation == Orientation.HORIZONTAL ? ship.y : ship.y+i;
            if (player.id == 1) {
                this.p1PlayingBoard.get(shipY).set(shipX, Status.BOAT);
            }
            if (player.id == 2) {
                this.p2PlayingBoard.get(shipY).set(shipX, Status.BOAT);
            }
        }
    }

    public void placeTemp(Ship ship){
        int length = ship.getLength();
        for (int i = 0; i < length; i++) {
            int shipX = ship.orientation == Orientation.HORIZONTAL ? ship.x + i : ship.x;
            int shipY = ship.orientation == Orientation.HORIZONTAL ? ship.y : ship.y+i;
            this.tempBoard.get(shipY).set(shipX, Status.BOAT);
        }
    }

    public void clearTempBoard() {
        this.tempBoard.clear();
        this.tempBoard = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            this.tempBoard.add(new ArrayList<>());
            for (int j = 0; j < 10; j++) {
                this.tempBoard.get(i).add(Status.EMPTY);
            }
        }
    }

    public void setP1PlayingBoard(ArrayList<ArrayList<Status>> p1PlayingBoard) {
        this.p1PlayingBoard = p1PlayingBoard;
    }

    public void setP2PlayingBoard(ArrayList<ArrayList<Status>> p2PlayingBoard) {
        this.p2PlayingBoard = p2PlayingBoard;
    }

    public ArrayList<ArrayList<Status>> getP1PlayingBoard() {

        return this.p1PlayingBoard;
    }

    public ArrayList<ArrayList<Status>> getNewP1PlayingBoard() {
        ArrayList<ArrayList<Status>> temp = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            temp.add(new ArrayList<>());
            for (int j = 0; j < 10; j++) {
                temp.get(i).add(this.p1PlayingBoard.get(i).get(j));
            }
        }
        return temp;
    }

    public ArrayList<ArrayList<Status>> getNewP2PlayingBoard() {
        ArrayList<ArrayList<Status>> temp = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            temp.add(new ArrayList<>());
            for (int j = 0; j < 10; j++) {
                temp.get(i).add(this.p2PlayingBoard.get(i).get(j));
            }
        }
        return temp;
    }

    public ArrayList<ArrayList<Status>> getP2PlayingBoard() {
        return this.p2PlayingBoard;
    }

    public Status getStatus(ArrayList<ArrayList<Status>> e, int x, int y) {
        return e.get(x).get(y);
    }


    public void printP1Screen() {
        System.out.println("      Player 1's Radar");
//        System.out.println();
        for (ArrayList<Status> row : this.p1ViewBoard) {
            for (Status status : row) {
                Translation.printTranslated(status);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("      Player 1's Ocean");
//        System.out.println();
        for (ArrayList<Status> row : this.p1PlayingBoard) {
            for (Status status : row) {
                Translation.printTranslated(status);
            }
            System.out.println();
        }
    }

    public void printP2Screen() {
        System.out.println("      Player 2's Radar");
//        System.out.println(getP2PlayingBoard());

//        System.out.println();
        for (ArrayList<Status> row : this.p2ViewBoard) {
            for (Status status : row) {
                Translation.printTranslated(status);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("      Player 2's Ocean");
//        System.out.println();

        for (ArrayList<Status> row : this.p2PlayingBoard) {
            for (Status status : row) {
                Translation.printTranslated(status);
            }
            System.out.println();
        }
    }

    public void printTempBoard(){
        for (ArrayList<Status> row : this.tempBoard) {
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
    public void updateAfterAttack(int row, int col, Player attacker, Player defender, boolean hit){
        ArrayList<ArrayList<Status>> attackerView;
        if(attacker.id == 1){
            attackerView = this.p1ViewBoard;
        }else{
            attackerView = this.p2ViewBoard;
        }

        if(hit){
            attackerView.get(row).set(col, Status.HIT);
        }else{
            attackerView.get(row).set(col, Status.MISS);
        }

        ArrayList<ArrayList<Status>> defenderBoard;
        if(defender.id == 1){
            defenderBoard = this.p1PlayingBoard;
        }else{
            defenderBoard = this.p2PlayingBoard;
        }

        if(hit){
            defenderBoard.get(row).set(col, Status.HIT);
        }else{
            defenderBoard.get(row).set(col, Status.MISS);
        }

        clear();
        if(attacker.id == 1){
            printP1Screen();
        }else{
            printP2Screen();
        }
    }
}
