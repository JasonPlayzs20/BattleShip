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

    //REQUIRED methods
    public void attack(Player opponent) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter row to attack (a-j): ");
        int row = Translation.translate(input.nextLine());

        System.out.print("Enter column to attack (0-9): ");
        int col = input.nextInt();
        input.nextLine();


        //ask the board which ship is at this postion
        boolean hit = false;
        Ship target = opponent.getShipAt(row, col);
        if(target != null){
            target.hit();
            hit = true;
            System.out.println("Hit!");
            if(target.isSunk()){
                System.out.println("You sunk a ship!");
            }
        } else {
            System.out.println("Miss!");
        }

        board.updateAfterAttack(row, col, this, opponent, hit);
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

            //chck each part of the ship
            for(int i = 0 ; i < s.getLength(); i++){
                int shipRow = s.y;
                int shipCol = s.x;

                if(s.orientation == Orientation.HORIZONTAL){
                    shipCol += i;
                } else {
                    shipRow += i;
                }

                if(shipRow == row && shipCol == col){
                    return s; // HIT FOUND
                }
            }

        }
        return null; //MISS
    }

    public void addShip(Ship ship) {
        this.ships.add(ship);
    }
}
