import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;


// Author: Dureti and Olivia
// Description: This class sets up the player's graphic design, and its movement based on an input direction
// Acknowledgements: COMP 128 professor 

public class Player extends Image {

    public static final double SIZE = 20;
    private boolean isMoving = true;
    public int direction = Direction.RIGHT;

    /**
     * Creates the image of the pacman. Sets its size, and centers it at a starting location in the maze.
     */
    public Player() {
        super("pacman_right.png");
        setMaxHeight(SIZE);
        setCenter((12*MazeCell.SIZE)+(MazeCell.SIZE/2), (16*MazeCell.SIZE)+(MazeCell.SIZE/2)); 
    }

    /**
     * 
     * @return the direction (left, right, up, down) of the player
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Updates the player's position based on the direction the player is travelling. 
     */
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

    /**
     * Sets the player's direction to be left 
     */
    public void moveLeft() {
        if (isMoving) {
            setImagePath("pacman_left.png");
            direction = Direction.LEFT;
        }
    }
    
    /**
     * Determines the position of the maze cell left of the player's current position
     * @return the point of the maze cell left of the player
     */
    public Point anticipateLeft() {
        return new Point(getCenter().getX()-MazeCell.SIZE, getCenter().getY());
    }

    /**
     * Sets the player's direction to be right 
     */
    public void moveRight() {
        if (isMoving) {
            setImagePath("pacman_right.png");
            direction = Direction.RIGHT;
        }
    }

    /**
     * Determines the position of the maze cell right of the player's current position
     * @return the point of the maze cell right of the player
     */
    public Point anticipateRight() {
        return new Point(getCenter().getX()+MazeCell.SIZE, getCenter().getY());
    }

    /**
     * Sets the player's direction to be up 
     */
    public void moveUp() {
        if (isMoving) {
            setImagePath("pacman_up.png");
            direction = Direction.UP;
        } 
    }

    /**
     * Determines the position of the maze cell up of the player's current position
     * @return the point of the maze cell up of the player
     */
    public Point anticipateUp() {
        return new Point(getCenter().getX(), getCenter().getY()-MazeCell.SIZE);
    }

    /**
     * Sets the player's direction to be down 
     */
    public void moveDown() {
        if (isMoving) {
            setImagePath("pacman_down.png");
            direction = Direction.DOWN;
        } 
    }

    /**
     * Determines the position of the maze cell down of the player's current position
     * @return the point of the maze cell down of the player
     */
    public Point anticipateDown() {
        return new Point(getCenter().getX(), getCenter().getY()+MazeCell.SIZE);
    }

    /**
     * prevents the movement of the player
     */
    public void stopMoving() {
        isMoving = false;

    }
    
    /**
     * allows for the player to move, after having been stopped
     */
    public void keepMoving() {
        isMoving = true;
    }

    public java.awt.Point getCellPosition() {
        return new java.awt.Point((int) (((getCenter().getX()-(MazeCell.SIZE/2))/MazeCell.SIZE)), (int) ((getCenter().getY()-(MazeCell.SIZE/2))/MazeCell.SIZE));
    }

    public int getCellX() {
        return getCellPosition().x;
    }

    public int getCellY() {
        return getCellPosition().y;
    }
}