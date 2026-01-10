public class Translation {
    public static void printTranslated(Status status) {
        if (status == Status.EMPTY) {
            System.out.print("[] ");
        }
        if (status == Status.HIT) {
            System.out.print("X ");
        }
        if (status == Status.MISS) {
            System.out.print("O ");
        }
        if (status == Status.SUNK) {
            System.out.print("- ");
        }
        if (status == Status.BOAT) {
            System.out.print("B ");
        }
    }

    public static String translate(Status status) {
        if (status == Status.EMPTY) {
            return "[] ";
        }
        if (status == Status.HIT) {
            return "X ";
        }
        if (status == Status.MISS) {
            return "O ";
        }
        if (status == Status.SUNK) {
            return "- ";
        }
        if (status == Status.BOAT) {
            return "B ";
        }
        return null;
    }

    public static int translate(String str) {
        if (str.equalsIgnoreCase("a")) return 0;
        if (str.equalsIgnoreCase("b")) return 1;
        if (str.equalsIgnoreCase("c")) return 2;
        if (str.equalsIgnoreCase("d")) return 3;
        if (str.equalsIgnoreCase("e")) return 4;
        if (str.equalsIgnoreCase("f")) return 5;
        if (str.equalsIgnoreCase("g")) return 6;
        if (str.equalsIgnoreCase("h")) return 7;
        if (str.equalsIgnoreCase("i")) return 8;
        if (str.equalsIgnoreCase("j")) return 9;
        return -1;
    }
}