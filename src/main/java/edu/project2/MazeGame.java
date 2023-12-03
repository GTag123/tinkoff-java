package edu.project2;

import java.util.List;
import java.util.Random;

public class MazeGame {
    private final Renderer renderer;
    private final Coordinate start;
    private final Coordinate end;
    private final Maze maze;
    private final List<Coordinate> path;

    public MazeGame(
        int h,
        int w,
        Generator generator,
        Solver solver,
        Renderer renderer,
        Coordinate start,
        Coordinate end
    ) {
        this.renderer = renderer;
        this.start = start;
        this.end = end;
        this.maze = generator.generate(h, w, new Random());
        this.path = solver.solve(maze, start, end);
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void start() {
        System.out.println("Сгенерированный лабиринт:");
        String mazeString = renderer.render(maze);
        System.out.println(mazeString);
        System.out.println();

        if (path.isEmpty()) {
            System.out.println("Путь не найден.");
        } else {
            System.out.println(
                "Путь от (" + start.row() + ", " + start.col() + ") до (" + end.row() + ", " + end.col() + "):");
            String pathString = renderer.render(maze, path);
            System.out.println(pathString);
        }
    }
}
