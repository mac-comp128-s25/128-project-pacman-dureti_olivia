import java.awt.Color;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.Key;

public class Pacman extends CanvasWindow {
    private static final int ANIMATE_DELAY = 10;
    private Player player;
    private Maze maze;
    private int animateTimer = 0;
    private int intVal = 0;
    private GraphicsText scoreCounter = new GraphicsText();
    private Rectangle scoreBackground;
    private int previousDirection =  4;

    /**
     * Draws the maze, scoreboard, and player to the canvas. Animates the player's movement
     */
    public Pacman() {
        super("Pacman", MazeCell.SIZE*25, MazeCell.SIZE*23);
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

        animate(() -> {
            animateTimer++;
            animateTimer %= ANIMATE_DELAY;
            int cellSize = MazeCell.SIZE;
            int mazeFactor = cellSize/2;

            // sets the scoreboard to update whenever a pellet or super pellet is consumed
            Boolean valuePellet = (maze.eatCellPellet((int) (((player.getCenter().getX()-mazeFactor)/(cellSize))), (int) ((player.getCenter().getY()-mazeFactor)/cellSize)));
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

            Point rightCell = player.anticipateRight();
            Point leftCell = player.anticipateLeft();
            Point upCell = player.anticipateUp();
            Point downCell = player.anticipateDown();

            boolean isRightPath = !maze.cellIsWall((int) (((rightCell.getX()-mazeFactor)/(cellSize))), (int) ((rightCell.getY()-mazeFactor)/cellSize));
            boolean isLeftPath = !maze.cellIsWall((int) (((leftCell.getX()-mazeFactor)/(cellSize))), (int) ((leftCell.getY()-mazeFactor)/cellSize));
            boolean isUpPath = !maze.cellIsWall((int) (((upCell.getX()-mazeFactor)/(cellSize))), (int) ((upCell.getY()-mazeFactor)/cellSize));
            boolean isDownPath = !maze.cellIsWall((int) (((downCell.getX()-mazeFactor)/(cellSize))), (int) ((downCell.getY()-mazeFactor)/cellSize));

            if (animateTimer == 0) {
                // The four if statements below state that the player only stops moving if the player's previous direction, 
                // before newly pressing a key in the direction of a wall, is also a wall.
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
        });

        // move in the direction of the key that was pressed
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

    public static void main(String[] args) {
        new Pacman();
    }
}