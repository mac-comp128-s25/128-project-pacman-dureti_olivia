import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;

public class Player extends Image {
    
    public static final double SIZE = 17;
    public final static double WAIT = 1000;

    private boolean isMoving = true;
    //
    //FIX BELOW
    //
    public int direction = Direction.RIGHT;
    ///
    /// 

    public Player() {
        super("pacman.png");
        setMaxHeight(SIZE);
        setCenter((12*MazeCell.SIZE)+(MazeCell.SIZE/2), (16*MazeCell.SIZE)+(MazeCell.SIZE/2)); 
    }

    public int getDirection() {
        return direction;
    }

    public void move() {
        final double x = getX();
        final double y = getY();
        switch (direction) {
            case Direction.LEFT  -> setX(x - MazeCell.SIZE);
            case Direction.RIGHT -> setX(x + MazeCell.SIZE);
            case Direction.UP    -> setY(y - MazeCell.SIZE);
            case Direction.DOWN  -> setY(y + MazeCell.SIZE);
            case Direction.NONE  -> stopMoving();
            default -> throw new IllegalArgumentException("invalid Direction");
        }
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
            }
            
            // else {
            //     direction = Direction.NONE;
            // }
    }

    public Point anticipateRight() {
        return new Point(getCenter().getX()+MazeCell.SIZE, getCenter().getY());
    }

    public void moveUp() {
        if (isMoving) {
            direction = Direction.UP;
            } 
            // else {
            //     direction = Direction.NONE;
            // }
    }

    public Point anticipateUp() {
        return new Point(getCenter().getX(), getCenter().getY()-MazeCell.SIZE);
    }

    public void moveDown() {
        if (isMoving) {
            direction = Direction.DOWN;
            } 
            // else {
            //     direction = Direction.NONE;
            // }
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