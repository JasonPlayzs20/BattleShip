import java.util.ArrayList;
import java.util.Arrays;

public class Ship{
    final ShipType shipType;
    int y;
    int x;
    private final int length;
    private int health;
    private boolean sunk;
    private Status status;
    Orientation orientation;
    ArrayList<Integer[]> locations;

    public Ship(Builder builder){
        this.shipType = builder.shipType;
        this.length = builder.length;
        this.health = builder.health;
        this.orientation = builder.orientation;
        this.sunk = false;
        this.status = Status.BOAT;
        this.x = builder.x;
        this.y = builder.y;
        this.locations = new ArrayList<>();
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean isShip(Integer[] location){
//        for(Integer[] loc : locations){
//            System.out.print(Arrays.toString(loc) + " ");
//        }
////        System.out.println(Arrays.toString(this.locations.toArray()));
//        System.out.println();
//        System.out.println(Arrays.toString(location));
        for(Integer[] loc : locations){
            if(loc[0].equals(location[1]) && loc[1].equals(location[0])) return true;
        }
        return false;
    }

    public static class Builder {
        private ShipType shipType;
        private int length;
        private int health;
        private int x;
        private int y;
        private Orientation orientation;

        public Builder setShipType(ShipType shipType) {
            this.shipType = shipType;
            this.length = this.shipType.length;
            this.health = shipType.length;
            return this;
        }

        public Builder setLength(int length) {
            this.length = length;
            return this;
        }

        public Builder setHealth(int health) {
            this.health = health;
            return this;
        }

        public Builder setX(int x) {
            this.x = x;
            return this;
        }

        public Builder setY(int y) {
            this.y = y;
            return this;
        }

        public Builder setOrientation(Orientation orientation) {
            this.orientation = orientation;
            return this;
        }

        public Ship build() {
            if (shipType == null || length < 0 || health < 0 || orientation == null) {

                throw new IllegalArgumentException("Construction Terminated: After careful and methodical verification of all provided parameters, the vessel has been declared non-constructible, as its proposed configuration does not adhere to the mandatory constraints, placement rules, and structural requirements prescribed by the governing construction framework."); // dont worry about this :)
            }
            return new Ship(this);
        }
    }



    public int getLength(){
        return length;
    }

    public int getHealth(){
        return health;
    }

    public boolean isSunk(){
        return sunk;
    }

    public ShipType shipType() {
        return this.shipType;
    }

    public void hit(){
        health--;
        if(health <= 0){
            sunk = true;
            this.status = Status.SUNK;
        }
    }
}