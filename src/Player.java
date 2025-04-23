import edu.macalester.graphics.Image;

public class Player extends Image {
    public static final double SIZE = 25;
    private double x;
    private double y;
    private final double MOVEMENT = 2;

    public Player() {
        super("pacman.png");
        setMaxHeight(SIZE);
        // this.x = x;
        // this.y = y;
    }

    public void moveLeft() {
        double x = getX() - MOVEMENT;

        setPosition(x, getY());
    }

    public void moveRight() {
        double x = getX() + MOVEMENT;

        setPosition(x, getY());
    }

    public void moveUp() {
        double y = getY() - MOVEMENT;

        setPosition(getX(), y);
    }

    public void moveDown() {
        double y = getY() + MOVEMENT;

        setPosition(getX(), y);
    }
}