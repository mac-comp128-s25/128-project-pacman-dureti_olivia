import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Rectangle;

public class MazeCell{
    private Boolean wall; // whether the tile is a wall
    private int row;
    private int col;

    public MazeCell(int i, int j, Boolean wall) {
        this.wall = wall;
        this.row = i;
        this.col = j;
    }

    public static MazeCell createWall() {
        return new MazeCell(row, col, true);
    }

    public static MazeCell createPath(boolean isSuperPellet) {
        return new MazeCell(false);
    }

    public boolean isWall() {
        return wall;
    }
}
