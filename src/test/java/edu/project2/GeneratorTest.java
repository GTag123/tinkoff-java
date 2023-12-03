package edu.project2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;

public class GeneratorTest {
    @Test
    @DisplayName("Test generate")
    public void testGenerate() {
        Maze maze = new MazeGenerator().generate(9, 9, new Random(7));
        String grid = new MazeRenderer().render(maze);
        assertThat(grid).isEqualTo("""
            ⬜⬜⬜⬜⬜⬜⬜⬜⬜
            ⬜⬛⬛⬛⬜⬛⬛⬛⬜
            ⬜⬛⬜⬜⬜⬛⬜⬜⬜
            ⬜⬛⬛⬛⬛⬛⬛⬛⬜
            ⬜⬛⬜⬛⬜⬛⬜⬜⬜
            ⬜⬛⬜⬛⬜⬛⬛⬛⬜
            ⬜⬜⬜⬜⬜⬛⬜⬜⬜
            ⬜⬛⬛⬛⬛⬛⬛⬛⬜
            ⬜⬜⬜⬜⬜⬜⬜⬜⬜""");
    }
}
