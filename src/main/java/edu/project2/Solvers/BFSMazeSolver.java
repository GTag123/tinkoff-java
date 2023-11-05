package edu.project2.Solvers;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.Solver;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSMazeSolver implements Solver {
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        boolean[][] visited = new boolean[maze.getHeight()][maze.getWidth()];
        Coordinate[][] parent = new Coordinate[maze.getHeight()][maze.getWidth()];
        Queue<Coordinate> queue = new LinkedList<>();

        queue.offer(start);
        visited[start.row()][start.col()] = true;

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            if (current.equals(end)) {
                path.add(current);
                while (!current.equals(start)) {
                    current = parent[current.row()][current.col()];
                    path.add(current);
                }
                return path;
            }

            for (int[] direction : DIRECTIONS) {
                int newRow = current.row() + direction[0];
                int newCol = current.col() + direction[1];

                if (isValidMove(maze, newRow, newCol) && !visited[newRow][newCol]) {
                    Coordinate neighbor = new Coordinate(newRow, newCol);
                    parent[newRow][newCol] = current;
                    visited[newRow][newCol] = true;
                    queue.offer(neighbor);
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

