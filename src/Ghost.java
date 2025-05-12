import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;

public abstract class Ghost extends Image {
    public static final double SIZE = 25;

    private int x, y;
    private int heading = Direction.NONE;

    public Ghost(int x, int y) {
        super("inky.png");
        setMaxHeight(SIZE);
        this.x = x;
        this.y = y;
        syncPosition();
    }

    public void syncPosition() {
        setCenter(x*MazeCell.SIZE+(MazeCell.SIZE/2), y*MazeCell.SIZE+(MazeCell.SIZE/2));
    }

    public int cellX() {
        return x;
    }

    public int cellY() {
        return y;
    }

    private int nextDirection() {
    }

    private abstract Point target() {

    }
}
