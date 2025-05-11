import java.awt.Color;
import java.util.Set;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.Key;

public class Pacman extends CanvasWindow {
    private static final int ANIMATE_DELAY = 15;

    private Player player;
    private Maze maze;

    private int animateTimer = 0;

    private int intVal = 0;
    private GraphicsText scoreCounter = new GraphicsText();
    private Rectangle scoreBackground;
    private int previousDirection =  5;
    

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
            
            int cellSize = MazeCell.SIZE;
            int mazeFactor = cellSize/2;

            Point rightCell = player.anticipateRight();
            Point leftCell = player.anticipateLeft();
            Point upCell = player.anticipateUp();
            Point downCell = player.anticipateDown();

            boolean isRightPath = !maze.cellIsWall((int) (((rightCell.getX()-mazeFactor)/(cellSize))), (int) ((rightCell.getY()-mazeFactor)/cellSize));
            boolean isLeftPath = !maze.cellIsWall((int) (((leftCell.getX()-mazeFactor)/(cellSize))), (int) ((leftCell.getY()-mazeFactor)/cellSize));
            boolean isUpPath = !maze.cellIsWall((int) (((upCell.getX()-mazeFactor)/(cellSize))), (int) ((upCell.getY()-mazeFactor)/cellSize));
            boolean isDownPath = !maze.cellIsWall((int) (((downCell.getX()-mazeFactor)/(cellSize))), (int) ((downCell.getY()-mazeFactor)/cellSize));

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

            animateTimer++;
            animateTimer %= ANIMATE_DELAY;

            
            if (animateTimer == 0) {

                // if you are going down an open path, and try to move right into a wall, don't stop moving --> instead, keep moving on this previous direction's path.
                
                        
                    if (player.getDirection() == Direction.RIGHT
                    && !isRightPath
                    ) {
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
                

                    else if (player.getDirection() == Direction.LEFT 
                    && !isLeftPath
                    ) {
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

                    else if (player.getDirection() == Direction.UP 
                    && !isUpPath
                    ) {
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