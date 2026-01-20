import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    //instance variables (top)
    private String name;
    private Board board;
    private ArrayList<Ship> ships;
    final int id;

    public Player(String name, Board board, int id) {
        this.name = name;
        this.board = board;
        this.id = id;
        this.ships = new ArrayList<>(); //initializes ship list
    }


    public void placeShips(Board board){
        ships = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        int[] shipLengths = {2, 3, 4};
        String[] shipNames = {"Destroyer", " Submarine", "Battleship"};

        for(int i = 0; i < shipLengths.length; i++){
            int length = shipLengths[i];
            String name = shipNames[i];
            boolean placed = false;

            while(!placed){
                System.out.println(this.name + ", place your " + name + " (length " + length + ")");

                System.out.print("Enter row (a-j): ");
                String rowString = input.nextLine();
                int y = Translation.translate(rowString);

                System.out.print("Enter column (0-9): ");
                int x = input.nextInt();
                input.nextLine();

                System.out.print("Enter orientation (H for horizontal, V for vertical): ");
                String origin = input.nextLine().toUpperCase();
                Orientation orientation;

                if(origin.equals("H")){
                    orientation = Orientation.HORIZONTAL;
                }else{
                    orientation = Orientation.VERTICAL;
                }

                Ship ship = Ship.builder().setLength(length).setHealth(length).setX(x).setY(y).setOrientation(orientation).setShipType(ShipType.values()[i]).build();

                if(board.canPlace(ship, this)){
                    board.place(ship, this);
                    ships.add(ship);
                    placed = true;
                    board.clear();
                    if(this.id == 1){
                        board.printP1Screen();
                    }else{
                        board.printP2Screen();
                    }
                }else{
                    System.out.println("Invalid placement! Try again.");
                }
            }
        }

    }
    public void addShip(Ship ship) {
        this.ships.add(ship);
    }
    //REQUIRED methods
    public void attack(Player opponent) {
        Scanner input = new Scanner(System.in);
        System.out.println("\n" + this.name + ", PREPARE TO FIRE!");

        int row;
        int col;

        do {
            System.out.print("Enter row to attack (a-j): ");
            row = Translation.translate(input.nextLine());
        } while (row == -1);

        do {
            System.out.print("Enter column to attack (0-9): ");
            while (!input.hasNextInt()) {
                input.next();
            }
            col = input.nextInt();
            input.nextLine();
        } while (col < 0 || col > 9);


        Ship target = opponent.getShipAt(row, col);// hallo, the ship selection and placement is fully workin :D; yea finally lmao; lol;
        boolean hit = (target != null);     //yo thats great man good stuff i was looking at it too and its working finally :), ikr it took so long smh

        for (Ship ship : opponent.ships) {
            Integer[] loc = {row, col};
            if (ship.isShip(loc)) {
                target = ship;
                target.hit();
                sout("DIRECT HIT!"); // YAAAAAAA :); btw u want me to paste in the print libary, so you only need to type sout("something to print"); sure yeah can u do that ?; un de sec; its in. just type sout(), soul() = System.out.print();
                if(target.isSunk()){
                    System.out.println("Target Neutralized: " + target.shipType + " SUNK!");
                }
                board.updateAfterAttack(row, col, this, opponent, hit);
                System.out.println("Here is your damage.");
                if (this.id == 1){
                    board.printP1Screen();
                }else{
                    board.printP2Screen();
                }

                return;
            }
        }
        System.out.println("SPLASH... It's a miss.");

//        if(hit){
//            target.hit();
//            sout("DIRECT HIT!"); // YAAAAAAA :); btw u want me to paste in the print libary, so you only need to type sout("something to print"); sure yeah can u do that ?; un de sec; its in. just type sout(), soul() = System.out.print();
//            if(target.isSunk()){
//                sout("Target Neutralized: " + target.shipType + " SUNK!");
//            }
//        }else{
//        }

        board.updateAfterAttack(row, col, this, opponent, hit);
//        board.clear();
//        GameControler.title();
        System.out.println("Here is your damage.");
        if (this.id == 1){
            board.printP1Screen();
        }else{
            board.printP2Screen();
        }

    }


    public Board getBoard() {
        return board;
    }


    public int getTotalShipsLeft() {
        int count = 0;
        for(Ship s : ships){
            if(!s.isSunk()){
                count++;
            }
        }
        return count;
    }

    public String getName() {
        return name;
    }

    public Ship getShipAt(int row, int col){
        for(Ship s : ships){
            for(int i = 0; i < s.getLength(); i++){
                int shipX = (s.orientation == Orientation.HORIZONTAL) ? s.x + i : s.x;
                int shipY = (s.orientation == Orientation.VERTICAL) ? s.y + i : s.y;

                if(shipY == row && shipX == col){
                    return s;
                }
            }
        }
        return null;
    }



    //print library
    public static void sout(String string) {
        System.out.println(string);
    }
    public static void sout() {
        System.out.println("");
    }
    public static void sout(boolean bool) {
        System.out.println(bool);
    }
    public static void sout(double doub) {
        System.out.println(doub);
    }
    public static void sout(float floa) {
        System.out.println(floa);
    }
    public static void sout(int in) {
        System.out.println(in);
    }
    public static void sout(char cha) {
        System.out.println(cha);
    }
    public static void sout(long lon) {
        System.out.println(lon);
    }
    public static void soul(String string) {
        System.out.print(string);
    }
    public static void soul() {
        System.out.print("");
    }
    public static void soul(int string) {
        System.out.print(string);
    }
    public static void soul(double string) {
        System.out.print(string);
    }
    public static void soul(char string) {
        System.out.print(string);
    }public static void soul(float string) {
        System.out.print(string);
    }
    public static void soul(long string) {
        System.out.print(string);
    }
    public static void soul(boolean string) {
        System.out.print(string);
    }



}
