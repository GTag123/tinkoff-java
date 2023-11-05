package edu.project2.Solvers;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.Solver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraMazeSolver implements Solver {
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        int[][] distance = new int[maze.getHeight()][maze.getWidth()];

        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {
                distance[row][col] = Integer.MAX_VALUE;
            }
        }

        distance[start.row()][start.col()] = 0;
        priorityQueue.add(new Node(start, 0));

        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();
            Coordinate currentCoordinate = current.coordinate;

            if (currentCoordinate.equals(end)) {
                Node trace = current;
                while (trace != null) {
                    path.add(trace.coordinate);
                    trace = trace.parent;
                }
                Collections.reverse(path);
                return path;
            }

            for (int[] direction : DIRECTIONS) {
                int newRow = currentCoordinate.row() + direction[0];
                int newCol = currentCoordinate.col() + direction[1];

                if (isValidMove(maze, newRow, newCol)) {
                    int newCost =
                        distance[currentCoordinate.row()][currentCoordinate.col()] + 1;

                    if (newCost < distance[newRow][newCol]) {
                        distance[newRow][newCol] = newCost;
                        priorityQueue.add(new Node(new Coordinate(newRow, newCol), newCost, current));
                    }
                }
            }
        }

        return path;
    }

    private boolean isValidMove(Maze maze, int row, int col) {
        return row >= 0 && row < maze.getHeight() && col >= 0 && col < maze.getWidth()
            && maze.getCell(row, col).type() == Cell.Type.PASSAGE;
    }

    private class Node {
        Coordinate coordinate;
        int cost;
        Node parent;

        Node(Coordinate coordinate, int cost) {
            this.coordinate = coordinate;
            this.cost = cost;
        }

        Node(Coordinate coordinate, int cost, Node parent) {
            this.coordinate = coordinate;
            this.cost = cost;
            this.parent = parent;
        }
    }
}
