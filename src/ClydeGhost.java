public class ClydeGhost extends Ghost {
    public ClydeGhost(int x, int y) {
        super(x, y, "clyde");
    }
    
    /**
     * Hunt pacman when far away, but flee back home when close!
     */
    protected java.awt.Point target(Maze maze, Player player) {
        if (getPos().distance(player.getCellPosition()) >= 6) {
            // Hunt while far away
            return player.getCellPosition();
        } else {
            // Flee when close
            return getHome();
        }
    }
}
