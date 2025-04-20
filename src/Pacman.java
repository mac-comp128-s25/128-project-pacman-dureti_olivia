import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;

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

    }

    

    public static void main(String[] args) {
        new Pacman();
    }
}