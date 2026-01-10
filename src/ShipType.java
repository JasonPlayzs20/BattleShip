public enum ShipType {
    CARRIER(5),
    BATTLESHIP(4),
    CRUISER(3),
    DESTROYER(2);

    final int length;
    ShipType(int length) {
        this.length = length;
    }


}
