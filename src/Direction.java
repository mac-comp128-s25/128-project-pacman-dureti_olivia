// Author: Dureti and Olivia
// Description: This class is used to set up the five different possible arrow directions a player can move in.
// Acknowledgements: COMP 128 professor 

public class Direction {
    public static final int UP = 0,
                            DOWN = 1,
                            LEFT = 2,
                            RIGHT = 3,
                            NONE = 4;

    public static int opposite(int direction) {
        return switch (direction) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
            default -> NONE;
        };
    }
}
