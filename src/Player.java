import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

public class Player extends Image {
    
    public static final double SIZE = 25;
    public final static double WAIT = 1000;

    private boolean isMoving = true;
    private int direction = Direction.UP;

    public Player() {
        super("pacman.png");
        setMaxHeight(SIZE);
    }

    public void move() {
        if (direction == Direction.LEFT) {
            double x = getX() - MazeCell.SIZE;
            setPosition(x, getY());
        } else if (direction == Direction.RIGHT) {
            double x = getX() + MazeCell.SIZE;
            setPosition(x, getY());
        } else if (direction == Direction.UP) {
            double y = getY() - MazeCell.SIZE;
            setPosition(getX(), y);
        } else if (direction == Direction.DOWN) {
            double y = getY() + MazeCell.SIZE;
            setPosition(getX(), y);
        }
    }

    // public void wait(CanvasWindow canvas) {
    //     canvas.pause(W);
    // }
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