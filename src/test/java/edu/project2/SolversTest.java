package edu.project2;

import edu.project2.solvers.BFSMazeSolver;
import edu.project2.solvers.DFSMazeSolver;
import edu.project2.solvers.DijkstraMazeSolver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class SolversTest {
    private static final String gridString =
            """
                    #########
                    #     # #
                    # # ### #
                    # #   # #
                    ### ### #
                    #     # #
                    ### ### #
                    #       #
                    #########""";
    private static List<Coordinate> DFSSolvedPath;
    private static List<Coordinate> BFSSolvedPath;
    private static List<Coordinate> DijkstraSolvedPath;

    private static final String badGridString = """
            #####
            #   #
            # ###
            # # #
            #####""";
    private static List<Coordinate> badDFSSolvedPath;
    private static List<Coordinate> badBFSSolvedPath;
    private static List<Coordinate> badDijkstraSolvedPath;

    private static Stream<Arguments> provideCoordinates() {
        return Stream.of(
            Arguments.of(1, 1),
            Arguments.of(1, 2),
            Arguments.of(1, 3),
            Arguments.of(2, 3),
            Arguments.of(3, 3),
            Arguments.of(4, 3),
            Arguments.of(5, 3),
            Arguments.of(6, 3),
            Arguments.of(7, 3),
            Arguments.of(7, 4),
            Arguments.of(7, 5),
            Arguments.of(7, 6),
            Arguments.of(7, 7)
        );
    }

    @BeforeAll
    static void initGrid() {
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(7, 7);

        Cell[][] grid = new Cell[9][9];
        Cell[][] badGrid = new Cell[5][5];

        for (int row = 0; row < 9; row++) {
            String rowString = gridString.split("\n")[row];
            for (int col = 0; col < 9; col++) {
                char cellChar = rowString.charAt(col);
                if (cellChar == '#') {
                    grid[row][col] = new Cell(row, col, Cell.Type.WALL);
                } else if (cellChar == ' ') {
                    grid[row][col] = new Cell(row, col, Cell.Type.PASSAGE);
                } else {
                    throw new IllegalArgumentException("Invalid character in grid string");
                }
            }
        }
        Maze maze = new Maze(9, 9, grid);
        DFSSolvedPath = new DFSMazeSolver().solve(maze, start, end);
        BFSSolvedPath = new BFSMazeSolver().solve(maze, start, end);
        DijkstraSolvedPath = new DijkstraMazeSolver().solve(maze, start, end);

        for (int row = 0; row < 5; row++) {
            String rowString = badGridString.split("\n")[row];
            for (int col = 0; col < 5; col++) {
                char cellChar = rowString.charAt(col);
                if (cellChar == '#') {
                    badGrid[row][col] = new Cell(row, col, Cell.Type.WALL);
                } else if (cellChar == ' ') {
                    badGrid[row][col] = new Cell(row, col, Cell.Type.PASSAGE);
                } else {
                    throw new IllegalArgumentException("Invalid character in grid string");
                }
            }
        }
        Maze badMaze = new Maze(5, 5, badGrid);
        badDFSSolvedPath = new DFSMazeSolver().solve(badMaze, start, end);
        badBFSSolvedPath = new BFSMazeSolver().solve(badMaze, start, end);
        badDijkstraSolvedPath = new DijkstraMazeSolver().solve(badMaze, start, end);
    }

    @ParameterizedTest
    @MethodSource("provideCoordinates")
    @DisplayName("Test DFS solver")
    void testDfsSolver(int row, int col) {
        assertThat(DFSSolvedPath).isNotEmpty();
        assertThat(DFSSolvedPath).hasSize(13);
        assertThat(DFSSolvedPath).contains(new Coordinate(row, col));
    }

    @ParameterizedTest
    @MethodSource("provideCoordinates")
    @DisplayName("Test DFS solver")
    void testBfsSolver(int row, int col) {
        assertThat(BFSSolvedPath).isNotEmpty();
        assertThat(BFSSolvedPath).hasSize(13);
        assertThat(BFSSolvedPath).contains(new Coordinate(row, col));
    }

    @ParameterizedTest
    @MethodSource("provideCoordinates")
    @DisplayName("Test Dijkstra solver")
    void testDijkstraSolver(int row, int col) {
        assertThat(DijkstraSolvedPath).isNotEmpty();
        assertThat(DijkstraSolvedPath).hasSize(13);
        assertThat(DijkstraSolvedPath).contains(new Coordinate(row, col));
    }

    @Test
    @DisplayName("Test not existing path")
    void testNotExistingPath() {
        assertThat(badDFSSolvedPath).isEmpty();
        assertThat(badBFSSolvedPath).isEmpty();
        assertThat(badDijkstraSolvedPath).isEmpty();
    }
}
