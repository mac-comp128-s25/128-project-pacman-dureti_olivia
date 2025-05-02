import java.io.InputStream;
import java.util.Scanner;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;

public class Maze {
    private MazeCell[][] mazeCells;
    private GraphicsGroup theMaze;

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

    public GraphicsGroup getMaze() {
        theMaze = new GraphicsGroup();
        for (MazeCell[] aRow : mazeCells) {
            for (GraphicsGroup aCell : aRow) {
                theMaze.add(aCell);
            }
        }
        return theMaze;
    }


    //x y mazeCell is a wall?
    public boolean cellIsWall(int x, int y) {

        MazeCell theCell = mazeCells[y][x];
        if (theCell.isWall()) {
            return true;
        }
        return false;
    }

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
