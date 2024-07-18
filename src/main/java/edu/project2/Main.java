package edu.project2;

import edu.project2.solvers.BFSMazeSolver;
import edu.project2.solvers.DFSMazeSolver;
import edu.project2.solvers.DijkstraMazeSolver;
import java.util.Scanner;

public class Main {
    private Main() {
    }

    private final static Scanner SCANNER = new Scanner(System.in);
    private final static int MIN_SIZE = 9;

    @SuppressWarnings({"RegexpSinglelineJava", "MagicNumber"})
    public static void main(String[] args) {
        System.out.printf("Введите высоту сетки (минимальный размер %d): ", MIN_SIZE);
        int height = SCANNER.nextInt();

        System.out.printf("Введите ширину сетки (минимальный размер %d): ", MIN_SIZE);
        int width = SCANNER.nextInt();

        if (!checkInput(height, width)) {
            System.out.println("Неверный ввод");
            System.exit(1);
        }

        System.out.printf("Выберите алгоритм решения\n"
            + "1 - Поиск в ширину\n"
            + "2 - Поиск в глубину\n"
            + "3 - Алгоритм Дейкстры\n");
        int algorithm = SCANNER.nextInt();
        Solver solver = null;
        switch (algorithm) {
            case 1:
                solver = new BFSMazeSolver();
                break;
            case 2:
                solver = new DFSMazeSolver();
                break;
            case 3:
                solver = new DijkstraMazeSolver();
                break;
            default:
                System.out.println("Неверный алгоритм");
                System.exit(1);
        }

        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(height - 2, width - 2);

        new MazeGame(height, width, new MazeGenerator(), solver, new MazeRenderer(), start, end).start();
    }

    private static boolean checkInput(int height, int width) {
        return height >= MIN_SIZE && width >= MIN_SIZE && height % 2 != 0 && width % 2 != 0;
    }
}
