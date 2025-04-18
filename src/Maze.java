import java.io.InputStream;
import java.util.Scanner;

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
                this.mazeCells[i][j].setPosition(i * MazeCell.SIZE, j * MazeCell.SIZE);
            }

        }
        input.close();
        
    }
}
