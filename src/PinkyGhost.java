public class PinkyGhost extends Ghost {
    public PinkyGhost(int x, int y) {
        super(x, y, "pinky");
    }
    
    /**
     * Target a point 4 cells in front of Pacman, to try to cut him off
     */
    protected java.awt.Point target(Maze maze, Player player) {
        java.awt.Point targetPos = player.getCellPosition();
        switch (player.direction) {
            case Direction.UP    -> targetPos.translate(0, -4);
            case Direction.DOWN  -> targetPos.translate(0, 4);
            case Direction.LEFT  -> targetPos.translate(-4, 0);
            case Direction.RIGHT -> targetPos.translate(4, 0);
        }
        return targetPos;
    }
}
