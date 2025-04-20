import java.io.InputStream;
import java.util.Scanner;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;

public class Maze {
    private MazeCell[][] mazeCells;

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
        GraphicsGroup theMaze = new GraphicsGroup();
        for (MazeCell[] aRow : mazeCells) {
            for (GraphicsGroup aCell : aRow) {
                theMaze.add(aCell);
            }

        }
        return theMaze;
    }



}
