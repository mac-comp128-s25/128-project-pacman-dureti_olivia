import java.awt.Color;
import java.util.Set;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.Key;

// Author: Dureti and Olivia
// Description: This class is the main window canvas that holds and animates the player and maze.
// Acknowledgements: COMP 128 professor 

public class Pacman extends CanvasWindow {
    private static final int ANIMATE_DELAY = 12;
    private Player player;
    private Maze maze;
    private Set<Ghost> ghosts;
    private int animateTimer = 0;
    private int intVal = 0;
    private GraphicsText scoreCounter = new GraphicsText();
    private Rectangle scoreBackground;
    private int previousDirection =  4;
    private final static int MAZEFACTOR = MazeCell.SIZE/2;

    /**
     * Draws the maze, scoreboard, and player to the canvas. Animates the player's movement
     */
    public Pacman() {
        // We can not get the size of the Maze from our Maze object due to a limitation with Java.
        // We are not allowed to referene attributes or methods of instances of classes until we call our constructor.
        // I have never used a language before with this restriction, but due to it, we need to hardcode these values.
        super("Pacman", MazeCell.SIZE*25, MazeCell.SIZE*23);
        createAssets();

        ghosts = Set.of(new BlinkyGhost(2, 2), new InkyGhost(2, 20), new PinkyGhost(22, 2), new ClydeGhost(22, 20));
        ghosts.forEach(this::add);

        animate(() -> {
            animateTimer++;
            animateTimer %= ANIMATE_DELAY;
            createScores();
            if (animateTimer == 0) {
                controlMovementStopping();
                ghosts.forEach(ghost -> ghost.move(maze, player));
            }
        });

        // move in the direction of the key that was pressed
        movePlayer();
    }

    /**
     * Moves the player in the direction of the arrow key that was pressed
     */
    public void movePlayer() {
        onKeyDown((keyEvent) -> {
            if (keyEvent.getKey().equals(Key.RIGHT_ARROW)) {
                    player.keepMoving();
                    player.moveRight();    
            }
            else if (keyEvent.getKey().equals(Key.LEFT_ARROW)) {
                    player.keepMoving();
                    player.moveLeft();    
            }
            else if (keyEvent.getKey().equals(Key.UP_ARROW)) {
                    player.keepMoving();
                    player.moveUp();    
            }
            else if (keyEvent.getKey().equals(Key.DOWN_ARROW)) {
                    player.keepMoving();
                    player.moveDown();    
            }
        });
    }

    /** 
     * Creates the scoreboard, maze, and player on the canvas
     */
    public void createAssets() {
        maze = new Maze();
        add(maze.getMaze());

        scoreBackground = new Rectangle((MazeCell.SIZE*20), 2, 120, MazeCell.SIZE-5);
        scoreBackground.setFillColor(Color.blue);
        scoreBackground.setStrokeColor(Color.white);
        add(scoreBackground);

        scoreCounter.setCenter((MazeCell.SIZE*21.5)-(MazeCell.SIZE/5), MazeCell.SIZE-(MazeCell.SIZE/6));
        scoreCounter.setFillColor(Color.white);
        scoreCounter.setText(String.valueOf(intVal));
        scoreCounter.setFontSize(MazeCell.SIZE-5);
        add(scoreCounter);

        player = new Player();
        add(player);
    }

    /**
     * Sets the scoreboard to update whenever a pellet or super pellet is consumed
     */
    public void createScores() {
        Boolean valuePellet = (maze.eatCellPellet(player.getCellPosition()));
        if (valuePellet!= null) {
            if (valuePellet) {
                intVal += 50;
                scoreCounter.setText(String.valueOf(intVal));
            }
            else if (!valuePellet) {
                intVal += 10;
                scoreCounter.setText(String.valueOf(intVal));
            }
        }
    }

    /**
     *  The four if statements below state that the player only stops moving if the player's previous direction, 
     *  before newly pressing a key in the direction of a wall, is also a wall.
     */
    public void controlMovementStopping() {
        Point rightCell = player.anticipateRight();
        Point leftCell = player.anticipateLeft();
        Point upCell = player.anticipateUp();
        Point downCell = player.anticipateDown();

        boolean isRightPath = !maze.cellIsWall((int) (((rightCell.getX()-MAZEFACTOR)/(MazeCell.SIZE))), (int) ((rightCell.getY()-MAZEFACTOR)/MazeCell.SIZE));
        boolean isLeftPath = !maze.cellIsWall((int) (((leftCell.getX()-MAZEFACTOR)/(MazeCell.SIZE))), (int) ((leftCell.getY()-MAZEFACTOR)/MazeCell.SIZE));
        boolean isUpPath = !maze.cellIsWall((int) (((upCell.getX()-MAZEFACTOR)/(MazeCell.SIZE))), (int) ((upCell.getY()-MAZEFACTOR)/MazeCell.SIZE));
        boolean isDownPath = !maze.cellIsWall((int) (((downCell.getX()-MAZEFACTOR)/(MazeCell.SIZE))), (int) ((downCell.getY()-MAZEFACTOR)/MazeCell.SIZE));

        if (player.getDirection() == Direction.RIGHT && !isRightPath) {
            if ((previousDirection == Direction.LEFT && !isLeftPath|| previousDirection == Direction.UP && !isUpPath || previousDirection == Direction.DOWN && !isDownPath) 
            && !isRightPath) {
                player.stopMoving();
            } else {
                if (previousDirection == Direction.LEFT) {
                    player.moveLeft();
                }
                if (previousDirection == Direction.UP) {
                    player.moveUp();
                }
                if (previousDirection == Direction.DOWN) {
                    player.moveDown();
                }
            }
        }
        else if (player.getDirection() == Direction.LEFT && !isLeftPath) {
            if ((previousDirection == Direction.RIGHT && !isRightPath|| previousDirection == Direction.UP && !isUpPath || previousDirection == Direction.DOWN && !isDownPath) 
            && !isLeftPath) {
                player.stopMoving();
            } else {
                if (previousDirection == Direction.RIGHT) {
                    player.moveRight();
                }
                if (previousDirection == Direction.UP) {
                    player.moveUp();
                }
                if (previousDirection == Direction.DOWN) {
                    player.moveDown();
                }
            }
        }
        else if (player.getDirection() == Direction.UP && !isUpPath) {
            if ((previousDirection == Direction.RIGHT && !isRightPath|| previousDirection == Direction.LEFT && !isLeftPath || previousDirection == Direction.DOWN && !isDownPath) 
            && !isUpPath) {
                player.stopMoving();
            } else {
                if (previousDirection == Direction.LEFT) {
                    player.moveLeft();
                }
                if (previousDirection == Direction.RIGHT) {
                    player.moveRight();
                }
                if (previousDirection == Direction.DOWN) {
                    player.moveDown();
                }
            }
        }
        else if (player.getDirection() == Direction.DOWN
        && !isDownPath
        ) {
            if ((previousDirection == Direction.RIGHT && !isRightPath|| previousDirection == Direction.UP && !isUpPath || previousDirection == Direction.LEFT && !isLeftPath) 
            && !isDownPath) {
                player.stopMoving();
            } else {
                if (previousDirection == Direction.LEFT) {
                    player.moveLeft();
                }
                if (previousDirection == Direction.UP) {
                    player.moveUp();
                }
                if (previousDirection == Direction.RIGHT) {
                    player.moveRight();
                }
            }
        }
        else {
            player.move();
        }
        previousDirection = player.getDirection();
    }

    public static void main(String[] args) {
        new Pacman();
    }
}