package edu.project2;

import java.util.List;

public class MazeGame {
    public static void main(String[] args) {
        // Задайте размеры лабиринта
        int height = 15;
        int width = 15;

        // Создайте генератор, решатель и отрисовщик лабиринта
        Generator generator = new MazeGenerator();
        Solver solver = new MazeSolver();
        Renderer renderer = new MazeRenderer();

        // Сгенерируйте лабиринт
        Maze maze = generator.generate(height, width);

        // Задайте начальную и конечную точки
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(height - 2, width - 2);

        // Решите лабиринт
        List<Coordinate> path = solver.solve(maze, start, end);

        // Отобразите лабиринт и путь в консоли
        System.out.println("Сгенерированный лабиринт:");
        String mazeString = renderer.render(maze);
        System.out.println(mazeString);

        if (path.isEmpty()) {
            System.out.println("Путь не найден.");
        } else {
            System.out.println("Путь от (" + start.row() + ", " + start.col() + ") до (" + end.row() + ", " + end.col() + "):");
            String pathString = renderer.render(maze, path);
            System.out.println(pathString);
        }
    }
}
