import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;

public class Pacman extends CanvasWindow {
    Player player;
    Maze maze;

    public Pacman() {
        super("Pacman", 1000, 1000);
        player = new Player();
        add(player);
        
    }

    

    public static void main(String[] args) {
        new Pacman();
    }
}