import java.awt.Color;
import java.awt.Paint;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;

public class MazeCell extends GraphicsGroup {
    public static final int SIZE = 52;
    private static final Paint WALL_COLOR = Color.BLUE;
    private static final Paint MAZE_COLOR = Color.BLACK;

    private Rectangle cellBackground;

    private boolean wall; // whether the tile is a wall

    private MazeCell(boolean wall) {
        this.wall = wall;
        cellBackground = new Rectangle(0, 0, SIZE, SIZE);
        cellBackground.setFillColor(wall ? WALL_COLOR : MAZE_COLOR);
        cellBackground.setStroked(false);
        add(cellBackground);
    }

    public static MazeCell createWall() {
        return new MazeCell(true);
    }

    public static MazeCell createPath(boolean isSuperPellet) {
        return new MazeCell(false);
    }

    public boolean isWall() {
        return wall;
    }
}
