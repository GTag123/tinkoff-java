package edu.project2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MazeTest {
    private static final String gridString = """
        #####
        #   #
        # ###
        #   #
        #####""";
    private static Maze maze;

    @BeforeAll
    public static void setMaze() {
        Cell[][] grid = new Cell[5][5];
        for (int row = 0; row < 5; row++) {
            String rowString = gridString.split("\n")[row];
            for (int col = 0; col < 5; col++) {
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
        maze = new Maze(5, 5, grid);
    }

    @Test
    @DisplayName("Test getHeight")
    public void testGetHeight() {
        assertThat(maze.getHeight()).isEqualTo(5);
    }

    @Test
    @DisplayName("Test getWidth")
    public void testGetWidth() {
        assertThat(maze.getWidth()).isEqualTo(5);
    }

    @Test
    @DisplayName("Test getCell good coordinates")
    public void testGetCell() {
        Cell wall = maze.getCell(0, 0);
        assertThat(wall.type()).isEqualTo(Cell.Type.WALL);

        Cell passage = maze.getCell(1, 1);
        assertThat(passage.type()).isEqualTo(Cell.Type.PASSAGE);
    }

    @Test
    @DisplayName("Test getCell bad coordinates")
    public void testGetCellBadCoordinates() {
        assertThatThrownBy(() -> maze.getCell(5, 5)).isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("Invalid coordinates");
    }

}
