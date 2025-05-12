public class InkyGhost extends Ghost {
    public InkyGhost(int x, int y) {
        super(x, y, "inky");
    }
    
    /**
     * This one is different than vanilla pacman! We use a Graph!
     */
    protected java.awt.Point target(Maze maze, Player player) {
        return getHome(); // placeholder
    }
}
