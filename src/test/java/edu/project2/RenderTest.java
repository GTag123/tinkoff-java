package edu.project2;

import edu.project2.Solvers.DFSMazeSolver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class RenderTest {
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
    private static Maze maze;
    private static List<Coordinate> path;

    @BeforeAll
    static void initGrid() {
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(7, 7);

        Cell[][] grid = new Cell[9][9];

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
        maze = new Maze(9, 9, grid);
        path = new DFSMazeSolver().solve(maze, start, end);
    }

    @Test
    @DisplayName("Test render maze")
    public void testRenderMaze() {
        String result = """
            ⬜⬜⬜⬜⬜⬜⬜⬜⬜
            ⬜⬛⬛⬛⬛⬛⬜⬛⬜
            ⬜⬛⬜⬛⬜⬜⬜⬛⬜
            ⬜⬛⬜⬛⬛⬛⬜⬛⬜
            ⬜⬜⬜⬛⬜⬜⬜⬛⬜
            ⬜⬛⬛⬛⬛⬛⬜⬛⬜
            ⬜⬜⬜⬛⬜⬜⬜⬛⬜
            ⬜⬛⬛⬛⬛⬛⬛⬛⬜
            ⬜⬜⬜⬜⬜⬜⬜⬜⬜""";

        String mazeString = new MazeRenderer().render(maze);
        assertThat(mazeString).isEqualTo(result);
    }

    @Test
    @DisplayName("Test render maze with path")
    public void testRenderMazeWithPath() {
        String result = """
            ⬜⬜⬜⬜⬜⬜⬜⬜⬜
            ⬜🟩🟩🟩⬛⬛⬜⬛⬜
            ⬜⬛⬜🟩⬜⬜⬜⬛⬜
            ⬜⬛⬜🟩⬛⬛⬜⬛⬜
            ⬜⬜⬜🟩⬜⬜⬜⬛⬜
            ⬜⬛⬛🟩⬛⬛⬜⬛⬜
            ⬜⬜⬜🟩⬜⬜⬜⬛⬜
            ⬜⬛⬛🟩🟩🟩🟩🟩⬜
            ⬜⬜⬜⬜⬜⬜⬜⬜⬜""";

        String mazeString = new MazeRenderer().render(maze, path);
        assertThat(mazeString).isEqualTo(result);
    }

}
