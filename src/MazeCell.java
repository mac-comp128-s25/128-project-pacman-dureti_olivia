public class MazeCell {
    private boolean wall; // whether the tile is a wall

    private MazeCell(boolean wall) {
        this.wall = wall;
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
