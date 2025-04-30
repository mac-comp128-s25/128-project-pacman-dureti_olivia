import edu.macalester.graphics.Image;

public class Player extends Image {
    public static final double SIZE = 25;
    private final double MOVEMENT = 2;

    private boolean isMoving = true;

    public Player() {
        super("pacman.png");
        setMaxHeight(SIZE);
    }

    public void moveLeft() {
        if (isMoving) {
            double x = getX() - MOVEMENT;
            setPosition(x, getY());
        }
    }

    public void moveRight() {
        if (isMoving) {
            double x = getX() + MOVEMENT;
            setPosition(x, getY());
        }
    }

    public void moveUp() {
        if (isMoving) {
            double y = getY() - MOVEMENT;
            setPosition(getX(), y);
        }
    }

    public void moveDown() {
        if (isMoving) {
            double y = getY() + MOVEMENT;
            setPosition(getX(), y);
        }
    }

    public void stopMoving() {
        isMoving = false;
    }
}