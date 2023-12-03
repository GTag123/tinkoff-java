package edu.project2;

import java.util.List;

public class MazeRenderer implements Renderer {
    private static final char PASSAGE_SYMBOL = '⬛';
    private static final char WALL_SYMBOL = '⬜';
    private static final String PATH_SYMBOL = "\uD83D\uDFE9";

    @Override
    public String render(Maze maze) {
        return render(maze, null);
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {
                Coordinate current = new Coordinate(row, col);
                if (path != null && path.contains(current)) {
                    sb.append(PATH_SYMBOL);
                } else {
                    Cell cell = maze.getCell(row, col);
                    if (cell.type() == Cell.Type.WALL) {
                        sb.append(WALL_SYMBOL);
                    } else {
                        sb.append(PASSAGE_SYMBOL);
                    }
                }
            }
            if (row != maze.getHeight() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
