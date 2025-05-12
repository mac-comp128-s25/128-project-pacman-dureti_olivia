public class BlinkyGhost extends Ghost {
    public BlinkyGhost(int x, int y) {
        super(x, y, "blinky");
    }
    
    protected java.awt.Point target(Maze maze, Player player) {
        return player.getCellPosition();
    }
}
