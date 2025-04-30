import edu.macalester.graphics.Image;

public class Player extends Image {
    public static final double SIZE = 25;
    private final double MOVEMENT = 5;

    private boolean isMoving = true;
    private int direction = Direction.UP;

    public Player() {
        super("pacman.png");
        setMaxHeight(SIZE);
    }

    public void animate() {
        if (direction == Direction.LEFT) {
            double x = getX() - MOVEMENT;
            setPosition(x, getY());
        } else if (direction == Direction.RIGHT) {
            double x = getX() + MOVEMENT;
            setPosition(x, getY());
        } else if (direction == Direction.UP) {
            double y = getY() - MOVEMENT;
            setPosition(getX(), y);
        } else if (direction == Direction.DOWN) {
            double y = getY() + MOVEMENT;
            setPosition(getX(), y);
        }
        return;
    }

    public void moveLeft() {
        direction = Direction.LEFT;
    }

    public void moveRight() {
        direction = Direction.RIGHT;
    }

    public void moveUp() {
        direction = Direction.UP;
    }

    public void moveDown() {
        direction = Direction.DOWN;
    }

    public void stopMoving() {
        isMoving = false;
    }
}