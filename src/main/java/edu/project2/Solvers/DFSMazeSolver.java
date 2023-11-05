package edu.project2.Solvers;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.Solver;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFSMazeSolver implements Solver {
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        Stack<Coordinate> stack = new Stack<>();
        boolean[][] visited = new boolean[maze.getHeight()][maze.getWidth()];
        Coordinate[][] parent = new Coordinate[maze.getHeight()][maze.getWidth()];
        stack.push(start);

        while (!stack.isEmpty()) {
            Coordinate current = stack.pop();
            int row = current.row();
            int col = current.col();
            visited[row][col] = true;

            if (current.equals(end)) {
                path.add(current);
                while (!current.equals(start)) {
                    current = parent[current.row()][current.col()];
                    path.add(current);
                }
                return path;
            }

            for (int[] direction : DIRECTIONS) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (isValidMove(maze, newRow, newCol) && !visited[newRow][newCol]) {
                    stack.push(new Coordinate(newRow, newCol));
                    parent[newRow][newCol] = current;
                }
            }
        }

        return path;
    }

    private boolean isValidMove(Maze maze, int row, int col) {
        return row >= 0 && row < maze.getHeight() && col >= 0 && col < maze.getWidth()
            && maze.getCell(row, col).type() == Cell.Type.PASSAGE;
    }
}
