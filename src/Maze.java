import java.io.InputStream;
import java.util.Scanner;
import edu.macalester.graphics.GraphicsGroup;


public class Maze {
    private MazeCell[][] mazeCells;
    private GraphicsGroup theMaze;

    /**
     * Reads the pacmanMaze in the res file, and sets each maze cell within this maze to have the corresponding
     * state of the cell, and position of the cell.
     */
    public Maze() {
        InputStream pacResource = Maze.class.getResourceAsStream("/pacmanMaze");
        Scanner input = new Scanner(pacResource);
        int R = input.nextInt();
        int C = input.nextInt();
        this.mazeCells = new MazeCell[R][C];
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                int n = input.nextInt();
                this.mazeCells[i][j] = MazeCell.fromInt(n);
                this.mazeCells[i][j].setPosition((j) * MazeCell.SIZE, (i) * MazeCell.SIZE);
            }
        }
        input.close();
    }

    /**
     * Compiles all the maze cells into a single maze.
     * @return the entire maze as a graphics group
     */
    public GraphicsGroup getMaze() {
        theMaze = new GraphicsGroup();
        for (MazeCell[] aRow : mazeCells) {
            for (GraphicsGroup aCell : aRow) {
                theMaze.add(aCell);
            }
        }
        return theMaze;
    }

    /**
     * If an input x and y coordinate, which will be used to insert the player's x and y coordinate, 
     * matches a certain maze cell's position, determines whether this is a wall or not.
     * @param x which is the column that the maze cell is placed in the mazeCells array.
     * @param y which is the row that the maze cell is placed in the mazeCells array.
     * @return true if the maze was a wall, and false if the maze was not a wall.
     */
    public boolean cellIsWall(int x, int y) {
        MazeCell theCell = mazeCells[y][x];
        if (theCell.isWall()) {
            return true;
        }
        return false;
    }

    /**
     * If an input x and y coordinate, which will be used to insert the player's x and y coordinate, 
     * matches a certain maze cell's position, removes the pellet at this maze, and returns true or false based on whether the pellet was
     * a super pellet or a normal pellet.
     * @param x which is the column that the maze cell is placed in the mazeCells array.
     * @param y  which is the row that the maze cell is placed in the mazeCells array.
     * @return true, if the maze cell contained a super pellet. False, if the maze cell contained a normal pellet. 
     * Null if the maze cell did not contain a pellet.
     * 
     */
    public Boolean eatCellPellet(int x, int y) {
        MazeCell theCell = mazeCells[y][x];
        Boolean value = theCell.eatPellet();

        if (value != null && value) {
            return true;
        } else if (value !=null && !value) {
            return false;
        }
        return null;
    }

}
