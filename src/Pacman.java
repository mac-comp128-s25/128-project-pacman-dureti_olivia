import edu.macalester.graphics.CanvasWindow;

public class Pacman extends CanvasWindow {
    Player player;

    public Pacman() {
        super("Pacman", 1000, 1000);
        player = new Player();
        add(player);
    }

    

    public static void main(String[] args) {
        new Pacman();
    }
}