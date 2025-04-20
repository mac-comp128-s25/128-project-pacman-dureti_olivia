import edu.macalester.graphics.Image;

public class Player extends Image {
    public static final double SIZE = 25;

    public Player() {
        super("pacman.png");
        setMaxHeight(SIZE);
    }
}