package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {
    @ParameterizedTest
    @CsvSource({
        "16, 1, 1",
        "17, 2, 6",
        "88, -2, -1"
    })
    @DisplayName("Test bitsRotateLeft")
    public void testRotateLeft(int number, int shift, int expected) {
        assertThat(Task7.rotateLeft(number, shift)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "8, 1, 4",
        "88, -2, -1"
    })
    @DisplayName("Test bitsRotateRight")
    public void testRotateRight(int number, int shift, int expected) {
        assertThat(Task7.rotateRight(number, shift)).isEqualTo(expected);
    }
}
