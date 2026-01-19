import java.util.*;

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
        currentPlayer =  this.player1;
        otherPlayer =  this.player2;
    }

    //starts the game loop
    public void startGame() {
        board.clear();
        this.title();
        System.out.println("Game starting!");

        while (!isGameOver()) {
//            System.out.println("ITS GAM ETIME");
            playTurn();
            switchTurns();

        }

        endGame();
    }

    public void title() {
        System.out.println("========================================");
        System.out.println("           BATTLESHIP: THE GRIND        ");
        System.out.println("========================================");
        sout();
        sout();
    }
    public void startSelectionPlayer1() {
       /*
        1, random selction to place ships
        2. arrowkeys to place
        3. repeat if needed
        */
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        this.board.tempBoard = this.board.getNewP1PlayingBoard();
        List<ShipType> ships = Arrays.asList(ShipType.CARRIER,ShipType.BATTLESHIP,ShipType.CRUISER, ShipType.DESTROYER);
        int choice = 0;
        Ship ship = null;
        int x = rand.nextInt(10);
        int y = rand.nextInt(10);
        while (true) {
            this.title();

//            System.out.println("in loop");

            this.board.tempBoard = this.board.getNewP1PlayingBoard();
//            System.out.println("Test this.board");
//            this.board.printP1Screen();
            if (choice >= ships.size()) {
//                this.board.setP1PlayingBoard(board.tempBoard);
                this.board.clearTempBoard();
                break;
            }
            System.out.println("Use: W,A,S,D to move your ship, Space or F to flip the ship, Nothing to secure it's position. ");
            System.out.println("Please press ENTER after every command.");
            System.out.println("If the ship does not flip, it can't be flipped at said location.");
            System.out.println();
            if (ship == null) {
                Orientation tempOr;
                if (rand.nextInt(2) == 1) {
                    tempOr = Orientation.HORIZONTAL;
                }
                else {
                    tempOr = Orientation.VERTICAL;
                }
                x = rand.nextInt(10);
                y = rand.nextInt(10);
                ship = Ship.builder().setX(x).setY(y).setShipType(ships.get(choice)).setOrientation(tempOr).build();
                while (true) {
                    if (!this.board.canPlace(ship,  this.player1)) {
//                        this.board.placeTemp(ship);
                        x = rand.nextInt(10);
                        y = rand.nextInt(10);
                        ship.x = x;
                        ship.y = y;
                    } else {
                        break;
                    }
                }
            }
            System.out.println("You are now captaining the " + ship.shipType.toString());

//            System.out.println("ship is not null");
//            this.board.printP1Screen();

            ship.x = x;
            ship.y = y;
//            System.out.println("setted ship x and y");
//            this.board.printP1Screen();

//            System.out.println("x: " + x + " y: " + y);
            if (this.board.canPlace(ship,  this.player1)) {
                this.board.placeTemp(ship);
            } else {
//                System.out.println("canmt pace");
//                scanner.nextLine();
                board.clear();
                continue;
            }
//            System.out.println("placed");
//            this.board.printP1Screen();

//            System.out.println(ship.y + " " + ship.x);
//            System.out.println("real:");
            this.board.printTempBoard();
            String inp = scanner.nextLine();
            if (inp.equalsIgnoreCase("")) {
                board.place(ship, player1);
                this.player1.addShip(ship);
                choice++;
                x = rand.nextInt(10);
                y = rand.nextInt(10);
                ship = null;
                this.board.clear();
                continue;
            } else if (inp.equalsIgnoreCase("w")) {
                ship.y--;
                if (!this.board.canPlace(ship, this.player1)){ship.y++;this.board.clear();continue;}
            } else if (inp.equalsIgnoreCase("a")) {
                ship.x--;
                if (!this.board.canPlace(ship, this.player1)){ship.x++;this.board.clear();continue;}
            } else if (inp.equalsIgnoreCase("s")) {
                ship.y++;
//                System.out.println(ship.y + " " + ship.x);
                if (!this.board.canPlace(ship, this.player1)){ship.y--;this.board.clear();continue;}
            } else if (inp.equalsIgnoreCase("d")) {
                ship.x++;
                if (!this.board.canPlace(ship, this.player1)){ship.y--;this.board.clear();continue;}
            } else if (inp.equalsIgnoreCase("f") || inp.equalsIgnoreCase(" ")) {
                ship.orientation = ship.orientation == Orientation.HORIZONTAL ? Orientation.VERTICAL : Orientation.HORIZONTAL;
                if (!this.board.canPlace(ship, this.player1)) {
                    ship.orientation = ship.orientation == Orientation.HORIZONTAL ? Orientation.VERTICAL : Orientation.HORIZONTAL;
                    this.board.clear();
                }

            }
            x = ship.x;
            y = ship.y;
//            System.out.println("end");
//            if (inp.equalsIgnoreCase("r")) {
//                x = rand.nextInt(10);
//                y = rand.nextInt(10);
//                while (true) {
//                    if (!board.canPlace(ship,  this.player1)) {
////                        this.board.placeTemp(ship);
//                        x = rand.nextInt(10);
//                        y = rand.nextInt(10);
////                        ship.x = x;
////                        ship.y = y;
//                    } else {
//                        break;
//                    }
//                }
//            }
            this.board.clear();


        }



    }

    public void startSelectionPlayer2() {
       /*
        1, random selction to place ships
        2. arrowkeys to place
        3. repeat if needed
        */
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        this.board.tempBoard = this.board.getNewP2PlayingBoard();
        List<ShipType> ships = Arrays.asList(ShipType.CARRIER,ShipType.BATTLESHIP,ShipType.CRUISER, ShipType.DESTROYER);
        int choice = 0;
        Ship ship = null;
        int x = rand.nextInt(10);
        int y = rand.nextInt(10);
        while (true) {
            this.title();
//            System.out.println("in loop");

            this.board.tempBoard = this.board.getNewP2PlayingBoard();
//            System.out.println("Test this.board");
//            this.board.printP2Screen();
            if (choice == ships.size()) {
//                this.board.setP2PlayingBoard(board.tempBoard);
                this.board.clearTempBoard();
                break;
            }
            System.out.println("Use: W,A,S,D to move your ship, Space or F to flip the ship, Nothing to secure it's position. ");
            System.out.println("Please press ENTER after every command.");
            System.out.println("If the ship does not flip, it can't be flipped at said location.");
            System.out.println();
            if (ship == null) {
                Orientation tempOr;
                if (rand.nextInt(2) == 1) {
                    tempOr = Orientation.HORIZONTAL;
                }
                else {
                    tempOr = Orientation.VERTICAL;
                }
                x = rand.nextInt(10);
                y = rand.nextInt(10);
                ship = Ship.builder().setX(x).setY(y).setShipType(ships.get(choice)).setOrientation(tempOr).build();
                while (true) {
                    if (!this.board.canPlace(ship,  this.player2)) {
//                        this.board.placeTemp(ship);
                        x = rand.nextInt(10);
                        y = rand.nextInt(10);
                        ship.x = x;
                        ship.y = y;
                    } else {
                        break;
                    }
                }
            }
            System.out.println("You are now captaining the " + ship.shipType.toString());

//            System.out.println("ship is not null");
//            this.board.printP2Screen();

            ship.x = x;
            ship.y = y;
//            System.out.println("setted ship x and y");
//            this.board.printP2Screen();

//            System.out.println("x: " + x + " y: " + y);
            if (this.board.canPlace(ship,  this.player2)) {
                this.board.placeTemp(ship);
            } else {
//                System.out.println("canmt pace");
//                scanner.nextLine();
                board.clear();
                continue;
            }
//            System.out.println("placed");
//            this.board.printP2Screen();

//            System.out.println(ship.y + " " + ship.x);
//            System.out.println("real:");
            this.board.printTempBoard();
            String inp = scanner.nextLine();
            if (inp.equalsIgnoreCase("")) {
                board.place(ship, player2);
                this.player2.addShip(ship);
                choice++;
                x = rand.nextInt(10);
                y = rand.nextInt(10);
                ship = null;
                this.board.clear();
                continue;
            } else if (inp.equalsIgnoreCase("w")) {
                ship.y--;
                if (!this.board.canPlace(ship, this.player2)){ship.y++;this.board.clear();continue;}
            } else if (inp.equalsIgnoreCase("a")) {
                ship.x--;
                if (!this.board.canPlace(ship, this.player2)){ship.x++;this.board.clear();continue;}
            } else if (inp.equalsIgnoreCase("s")) {
                ship.y++;
//                System.out.println(ship.y + " " + ship.x);
                if (!this.board.canPlace(ship, this.player2)){ship.y--;this.board.clear();continue;}
            } else if (inp.equalsIgnoreCase("d")) {
                ship.x++;
                if (!this.board.canPlace(ship, this.player2)){ship.y--;this.board.clear();continue;}
            } else if (inp.equalsIgnoreCase("f") || inp.equalsIgnoreCase(" ")) {
                ship.orientation = ship.orientation == Orientation.HORIZONTAL ? Orientation.VERTICAL : Orientation.HORIZONTAL;
                if (!this.board.canPlace(ship, this.player2)) {
                    ship.orientation = ship.orientation == Orientation.HORIZONTAL ? Orientation.VERTICAL : Orientation.HORIZONTAL;
                    this.board.clear();
                }

            }
            x = ship.x;
            y = ship.y;
//            System.out.println("end");
//            if (inp.equalsIgnoreCase("r")) {
//                x = rand.nextInt(20);
//                y = rand.nextInt(10);
//                while (true) {
//                    if (!board.canPlace(ship,  this.player1)) {
////                        this.board.placeTemp(ship);
//                        x = rand.nextInt(10);
//                        y = rand.nextInt(10);
////                        ship.x = x;
////                        ship.y = y;
//                    } else {
//                        break;
//                    }
//                }
//            }
            this.board.clear();


        }



    }
    //one full turn
    public void playTurn() {
        System.out.println("\n" + currentPlayer.getName() + "'s turn");
        if (isPlayer1) {
            this.board.printP1Screen();
        } else  {
            this.board.printP2Screen();
        }
        //player attacks the other player
        currentPlayer.attack(otherPlayer);
//        switchTurns();
        //show opponent this.board AFTER attack)


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
