import java.util.Set;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.events.Key;

public class Pacman extends CanvasWindow {
    Player player;
    Maze maze;
    MazeCell mazeCell;

    public Pacman() {
        super("Pacman", 40*23, 40*21);

        maze = new Maze();
        add(maze.getMaze());

        player = new Player();

        player.setCenter((11*MazeCell.SIZE)+(MazeCell.SIZE/2), (15*MazeCell.SIZE)+(MazeCell.SIZE/2)); 
        add(player);
        animate(player::animate);

        animate((e2) -> {
            final Set<Key> keys = getKeysPressed();
            if (keys.contains(Key.RIGHT_ARROW)) {
                player.moveRight();
            }
            if (keys.contains(Key.LEFT_ARROW)) {
                    player.moveLeft();
            }
            if (keys.contains(Key.UP_ARROW)) {
                    player.moveUp();
            }
            if (keys.contains(Key.DOWN_ARROW)) {
                    player.moveDown();
            }
        });

        // animate((e5) -> {
        //     if (maze.cellIsWall((int) player.getX(), (int) player.getY())) {
        //         player.stopMoving();

        //     }
      
        //     // if (getElementAt(player.getX(), player.getY()) instanceof ) {
        //     //     player.stopMoving();
        //     // }
        // });
    }


    public static void main(String[] args) {
        new Pacman();
    }
}