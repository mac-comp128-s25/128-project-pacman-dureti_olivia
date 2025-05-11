
import java.awt.Color;
import java.awt.Paint;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;

public class MazeCell extends GraphicsGroup {
    public static final int SIZE = 30;
    private static final Paint WALL_COLOR = Color.BLUE;
    private static final Paint MAZE_COLOR = Color.BLACK;
    private static final Paint PELLET_COLOR = Color.YELLOW;
    private static final double PELLET_SIZE = 6;
    private static final double SUPER_PELLET_SIZE = 13;
    private Rectangle cellBackground;
    private Ellipse pellet;
    private final boolean wall; // whether the tile is a wall
    private Boolean pelletState; // null when pellet is gone, true when super, false when normal

    /**
     * The graphic design of the maze cell is created here, based on whether it is a wall, path, and has pellets.
     * @param wall
     * @param pelletState
     */
    private MazeCell(boolean wall, Boolean pelletState) {
        this.wall = wall;
        this.pelletState = pelletState;
        cellBackground = new Rectangle(0, 0, SIZE, SIZE);
        cellBackground.setFillColor(MAZE_COLOR);
        cellBackground.setStrokeColor(WALL_COLOR);
        cellBackground.setStroked(isWall());
        add(cellBackground);
        if (hasPellet()) {
            final double size = hasSuperPellet() ? SUPER_PELLET_SIZE : PELLET_SIZE;
            pellet = new Ellipse(0, 0, size, size);
            pellet.setCenter(getCenter());
            pellet.setFillColor(PELLET_COLOR);
            pellet.setStrokeColor(PELLET_COLOR);
            add(pellet);
        }
    }

    /**
     * Takes an integer, from the pacmanMaze res folder, and creates a maze cell based on this integer value
     * @param code
     * @return a maze cell with the correct state (wall, path, empty).
     */
    public static MazeCell fromInt(int code) {
        return switch (code) {
            case 0 -> createPath(false);
            case 1 -> createWall();
            case 2 -> createPath(true);
            case 3 -> createPath(null);
            default -> throw new IllegalArgumentException("code out of bounds");
        };
    }

    /**
     * Creates a new maze cell that is a wall
     * @return a maze cell that is a wall
     */
    public static MazeCell createWall() {
        return new MazeCell(true, null);
    }

    /**
     * Creates a new maze cell that is a path
     * @param isSuperPellet
     * @return a maze cell that is a path, meaning it is not a wall, and may contain some pellet type.
     */
    public static MazeCell createPath(Boolean isSuperPellet) {
        return new MazeCell(false, isSuperPellet);
    }

    /**
     * Determines whether a maze cell is a wall
     * @return true if a maze cell is a wall, and false if not.
     */
    public boolean isWall() {
        return wall;
    }

    /**
     *  Determines whether a maze cell contains a pellet of any kind.
     * @return true if the maze contains a pellet of any kind, and false if not.
     */
    public boolean hasPellet() {
        return pelletState != null;
    }

    /**
     *  Determines whether a maze cell contains a normal pellet. Returns true if this maze cell contains a normal pellet.
     * @return true if the maze cells contains a normal pellet, and false if not.
     */
    public boolean hasNormalPellet() {
        return hasPellet() && !pelletState.booleanValue();
    }

    /**
     *  Determines whether a maze cell contains a super pellet. Returns true if this maze cell contains a super pellet.
     * @return true if the maze cell contains a super pellet, and false if not.
     */
    public boolean hasSuperPellet() {
        return hasPellet() && pelletState.booleanValue();
    }

    /**
     * Removes a pellet from this Maze Cell
     * @return true if the pellet was a super pellet, and returns false if the pellet was a normal pellet
     */
    public Boolean eatPellet() {
        if (hasPellet()) {
            remove(pellet);
            Boolean ret = pelletState;
            pellet = null;
            pelletState = null;
            return ret;
        } else {
            return null;
        }
    }
}
