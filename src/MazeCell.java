
import java.awt.Color;
import java.awt.Paint;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

public class MazeCell extends GraphicsGroup {
    public static final int SIZE = 40;
    private static final Paint WALL_COLOR = Color.BLUE;
    private static final Paint MAZE_COLOR = Color.BLACK;
    private static final Paint PELLET_COLOR = Color.YELLOW;
    private static final double PELLET_SIZE = 6;
    private static final double SUPER_PELLET_SIZE = 15;

    private Rectangle cellBackground;
    private Ellipse pellet;

    private final boolean wall; // whether the tile is a wall
    private Boolean pelletState; // null when pellet is gone, true when super, false when normal

    private MazeCell(boolean wall, Boolean pelletState) {
        this.wall = wall;
        this.pelletState = pelletState;
        cellBackground = new Rectangle(0, 0, SIZE, SIZE);
        // cellBackground.setFillColor(wall ? WALL_COLOR : MAZE_COLOR);
        cellBackground.setFillColor(Color.BLACK);
        cellBackground.setStroked(true);
        cellBackground.setStrokeColor(Color.BLUE);
        add(cellBackground);
        if (hasPellet()) {
            cellBackground.setStroked(false);

            final double size = hasSuperPellet() ? SUPER_PELLET_SIZE : PELLET_SIZE;
            pellet = new Ellipse(0, 0, size, size);
            pellet.setCenter(getCenter());
            pellet.setFillColor(PELLET_COLOR);
            pellet.setStrokeColor(PELLET_COLOR);
            add(pellet);
        }
    }

    public static MazeCell fromInt(int code) {
        return switch (code) {
            case 1 -> createWall();
            case 0 -> createPath(false);
            case 2 -> createPath(true);
            default -> throw new IllegalArgumentException("code out of bounds");
        };
    }

    public static MazeCell createWall() {
        return new MazeCell(true, null);
    }

    public static MazeCell createPath(boolean isSuperPellet) {
        return new MazeCell(false, isSuperPellet);
    }

    public boolean isWall() {
        return wall;
    }

    public boolean hasPellet() {
        return pelletState != null;
    }

    public boolean hasNormalPellet() {
        return hasPellet() && !pelletState.booleanValue();
    }

    public boolean hasSuperPellet() {
        return hasPellet() && pelletState.booleanValue();
    }
}
