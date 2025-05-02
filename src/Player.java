import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;

public class Player extends Image {
    
    public static final double SIZE = 25;
    public final static double WAIT = 1000;

    private boolean isMoving = true;
    private int direction = Direction.RIGHT;
    // private MazeCell mazeCell;

    public Player() {
        super("pacman.png");
        setMaxHeight(SIZE);
        setCenter((11*MazeCell.SIZE)+(MazeCell.SIZE/2), (15*MazeCell.SIZE)+(MazeCell.SIZE/2)); 
    }

    public int getDirection() {
        return direction;
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
        else if (direction == Direction.NONE) {
            stopMoving();

        }

        // }
        // else if (getElementAt((getX()/(40*getX())), (getY()/(40*getY()))) ) {
        //     MazeCell.eatPellet();
        // }

    }

  
    public void moveLeft() {
        if (isMoving) {
        direction = Direction.LEFT;
        } else {
            direction = Direction.NONE;
        }
    }
    
    public Point anticipateLeft() {
        return new Point(getCenter().getX()-MazeCell.SIZE, getCenter().getY());
    }

    public void moveRight() {
        if (isMoving) {
            direction = Direction.RIGHT;
            } else {
                direction = Direction.NONE;
            }
    }

    public Point anticipateRight() {
        return new Point(getCenter().getX()+MazeCell.SIZE, getCenter().getY());
    }

    public void moveUp() {
        if (isMoving) {
            direction = Direction.UP;
            } else {
                direction = Direction.NONE;
            }
    }

    public Point anticipateUp() {
        return new Point(getCenter().getX(), getCenter().getY()-MazeCell.SIZE);
    }

    public void moveDown() {
        if (isMoving) {
            direction = Direction.DOWN;
            } else {
                direction = Direction.NONE;
            }
    }

    public Point anticipateDown() {
        return new Point(getCenter().getX(), getCenter().getY()+MazeCell.SIZE);
    }

    public void stopMoving() {
        isMoving = false;

    }
    
    public void keepMoving() {
        isMoving = true;
    }
}