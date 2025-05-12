import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import edu.macalester.graphics.Image;

public abstract class Ghost extends Image {
    public static final double SIZE = 25;

    private java.awt.Point pos;
    private int heading = Direction.NONE;

    public Ghost(int x, int y, String sprite_name) {
        super(sprite_name.concat("_ghost.png"));
        setMaxHeight(SIZE);
        pos = new java.awt.Point(x, y);
        syncPosition();
    }

    public void syncPosition() {
        setCenter(pos.x*MazeCell.SIZE+(MazeCell.SIZE/2), pos.y*MazeCell.SIZE+(MazeCell.SIZE/2));
    }

    public int cellX() {
        return pos.x;
    }

    public int cellY() {
        return pos.y;
    }

    public java.awt.Point asPoint() {
        return new java.awt.Point(pos);
    }

    public void move(Maze maze, Player player) {
        heading = nextDirection(maze, player);
        switch (heading) {
            case Direction.UP    -> pos.translate(0, -1);
            case Direction.DOWN  -> pos.translate(0, 1);
            case Direction.LEFT  -> pos.translate(-1, 0);
            case Direction.RIGHT -> pos.translate(1, 0);
        }
        syncPosition();
    }

    private int nextDirection(Maze maze, Player player) {
        final Map<Integer, Double> directions = new HashMap<>();
        directions.put(Direction.UP,    distanceBetween(maze, player, new Point(pos.x,     pos.y - 1)));
        directions.put(Direction.DOWN,  distanceBetween(maze, player, new Point(pos.x,     pos.y + 1)));
        directions.put(Direction.LEFT,  distanceBetween(maze, player, new Point(pos.x - 1, pos.y    )));
        directions.put(Direction.RIGHT, distanceBetween(maze, player, new Point(pos.x + 1, pos.y    )));
        int ret = Direction.NONE;
        double bestDistance = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Double> entry : directions.entrySet()) {
            if (entry.getValue() < bestDistance && heading != Direction.opposite(entry.getKey())) {
                ret = entry.getKey();
                bestDistance = entry.getValue();
            }
        }
        return ret;
    }

    private double distanceBetween(Maze maze, Player player, java.awt.Point check) {
        if (maze.cellIsWall(check.x, check.y)) {
            return Integer.MAX_VALUE;
        } else {
            return check.distance(target(maze, player));
        }
    }

    protected abstract java.awt.Point target(Maze maze, Player player);
}
