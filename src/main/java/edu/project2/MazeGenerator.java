package edu.project2;

import java.util.Random;
import java.util.Stack;

public class MazeGenerator implements Generator {
    private static final int[][] DIRECTIONS = {{0, 2}, {0, -2}, {2, 0}, {-2, 0}};

    @Override
    public Maze generate(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(row, col, Cell.Type.WALL);
            }
        }

        Random random = new Random();
        Stack<Coordinate> stack = new Stack<>();
        int startRow = 1;
        int startCol = 1;
        int endRow = height - 2;
        int endCol = width - 2;
        Coordinate current = new Coordinate(startRow, startCol);
        grid[startRow][startCol] = new Cell(startRow, startCol, Cell.Type.PASSAGE);
        stack.push(current);

        while (!stack.isEmpty()) {
            current = stack.peek();
            int row = current.row();
            int col = current.col();
            boolean hasUnvisitedNeighbors = false;

            shuffleArray(DIRECTIONS, random);

            for (int[] direction : DIRECTIONS) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (newRow >= 1 && newRow <= endRow && newCol >= 1 && newCol <= endCol) {
                    if (grid[newRow][newCol].type() == Cell.Type.WALL) {
                        int wallRow = row + direction[0] / 2;
                        int wallCol = col + direction[1] / 2;
                        grid[newRow][newCol] = new Cell(newRow, newCol, Cell.Type.PASSAGE);
                        grid[wallRow][wallCol] = new Cell(wallRow, wallCol, Cell.Type.PASSAGE);
                        stack.push(new Coordinate(newRow, newCol));
                        hasUnvisitedNeighbors = true;
                    }
                }
            }

            if (!hasUnvisitedNeighbors) {
                stack.pop();
            }
        }

        return new Maze(height, width, grid);
    }

    private void shuffleArray(int[][] arr, Random random) {
        for (int i = arr.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int[] temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }
}

