public class BlinkyGhost extends Ghost {
    public BlinkyGhost(int x, int y) {
        super(x, y, "blinky");
    }
    
    /**
     * Target Pacman's position
     */
    protected java.awt.Point target(Maze maze, Player player) {
        return player.getCellPosition();
    }
}
