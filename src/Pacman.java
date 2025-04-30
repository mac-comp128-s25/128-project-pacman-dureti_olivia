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
        super("Pacman", 1500, 1500);

        maze = new Maze();
        add(maze.getMaze());

        player = new Player();

        player.setCenter((11*MazeCell.SIZE)+(MazeCell.SIZE/2), (15*MazeCell.SIZE)+(MazeCell.SIZE/2)); 
        add(player);

        animate((e2) -> {
            if (getKeysPressed().contains(Key.RIGHT_ARROW)) {
                    player.moveRight();
            }});
        
        animate((e1) -> {
            if (getKeysPressed().contains(Key.LEFT_ARROW)) {
                    player.moveLeft();
            }});

        animate((e3) -> {
            if (getKeysPressed().contains(Key.UP_ARROW)) {
                    player.moveUp();
            }});

        animate((e4) -> {
            if (getKeysPressed().contains(Key.DOWN_ARROW)) {
                    player.moveDown();
            }});

        // animate((e5) -> {
        //     if (maze.cellIsWall((int) player.getX(), (int) player.getY())) {
        //         player.stopMoving();

        //     }
        // });

    }


    public static void main(String[] args) {
        new Pacman();
    }
}