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
        System.out.println("Game starting!");

        while (!isGameOver()) {
            playTurn();
            switchTurns();

        }

        endGame();
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
//            System.out.println("in loop");
            this.board.tempBoard = this.board.getNewP1PlayingBoard();
//            System.out.println("Test this.board");
//            this.board.printP1Screen();
            if (choice == ships.size()) {
                this.board.setP1PlayingBoard(board.tempBoard);
                this.board.clearTempBoard();
                break;
            }

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
                scanner.nextLine();
                continue;
            }
//            System.out.println("placed");
//            this.board.printP1Screen();

//            System.out.println(ship.y + " " + ship.x);
//            System.out.println("real:");
            this.board.printTempBoard();
            String inp = scanner.nextLine();
            if (inp.equalsIgnoreCase("")) {
                this.board.place(ship, this.player1);
                 this.player1.addShip(ship);
                choice++;
                x = rand.nextInt(10);
                y = rand.nextInt(10);
                ship = null;
                continue;
            } else if (inp.equalsIgnoreCase("w")) {
                ship.y--;
                if (!this.board.canPlace(ship, this.player1)){ship.y++;continue;}
            } else if (inp.equalsIgnoreCase("a")) {
                ship.x--;
                if (!this.board.canPlace(ship, this.player1)){ship.x++;continue;}
            } else if (inp.equalsIgnoreCase("s")) {
                ship.y++;
//                System.out.println(ship.y + " " + ship.x);
                if (!this.board.canPlace(ship, this.player1)){ship.y--;continue;}
            } else if (inp.equalsIgnoreCase("d")) {
                ship.x++;
                if (!this.board.canPlace(ship, this.player1)){ship.y--;continue;}
            } else if (inp.equalsIgnoreCase("f") || inp.equalsIgnoreCase(" ")) {
                ship.orientation = ship.orientation == Orientation.HORIZONTAL ? Orientation.VERTICAL : Orientation.HORIZONTAL;
                if (!this.board.canPlace(ship, this.player1)) {
                    ship.orientation = ship.orientation == Orientation.HORIZONTAL ? Orientation.VERTICAL : Orientation.HORIZONTAL;
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
//            System.out.println("in loop");
            this.board.tempBoard = this.board.getNewP2PlayingBoard();
//            System.out.println("Test this.board");
//            this.board.printP2Screen();
            if (choice == ships.size()) {
                this.board.setP2PlayingBoard(board.tempBoard);
                this.board.clearTempBoard();
                break;
            }

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
                    if (!board.canPlace(ship,  this.player2)) {
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
//            System.out.println("ship is not null");
//            this.board.printP2Screen();

            ship.x = x;
            ship.y = y;
//            System.out.println("setted ship x and y");
//            this.board.printP2Screen();

//            System.out.println("x: " + x + " y: " + y);
            if (board.canPlace(ship,  this.player2)) {
                this.board.placeTemp(ship);
            } else {
//                System.out.println("canmt pace");
                scanner.nextLine();
                continue;
            }
//            System.out.println("placed");
//            this.board.printP2Screen();

//            System.out.println(ship.y + " " + ship.x);
//            System.out.println("real:");
            this.board.printTempBoard();
            String inp = scanner.nextLine();
            if (inp.equalsIgnoreCase("")) {
                this.board.place(ship, this.player2);
                choice++;
                x = rand.nextInt(10);
                y = rand.nextInt(10);
                ship = null;
                continue;
            } else if (inp.equalsIgnoreCase("w")) {
                ship.y--;
                if (!board.canPlace(ship, this.player2)){ship.y++;continue;}
            } else if (inp.equalsIgnoreCase("a")) {
                ship.x--;
                if (!board.canPlace(ship, this.player2)){ship.x++;continue;}
            } else if (inp.equalsIgnoreCase("s")) {
                ship.y++;
//                System.out.println(ship.y + " " + ship.x);
                if (!board.canPlace(ship, this.player2)){ship.y--;continue;}
            } else if (inp.equalsIgnoreCase("d")) {
                ship.x++;
                if (!board.canPlace(ship, this.player2)){ship.y--;continue;}
            } else if (inp.equalsIgnoreCase("f") || inp.equalsIgnoreCase(" ")) {
                ship.orientation = ship.orientation == Orientation.HORIZONTAL ? Orientation.VERTICAL : Orientation.HORIZONTAL;
                if (!board.canPlace(ship, this.player2)) {
                    ship.orientation = ship.orientation == Orientation.HORIZONTAL ? Orientation.VERTICAL : Orientation.HORIZONTAL;
                }

            }
            x = ship.x;
            y = ship.y;
//            System.out.println("end");
//            if (inp.equalsIgnoreCase("r")) {
//                x = rand.nextInt(10);
//                y = rand.nextInt(10);
//                while (true) {
//                    if (!board.canPlace(ship,  this.player2)) {
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

        //player attacks the other player
        currentPlayer.attack(otherPlayer);
        switchTurns();
        //show opponent this.board AFTER attack)
        if (isPlayer1) {
            this.board.printP1Screen();
        } else  {
            this.board.printP2Screen();
        }

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



}
