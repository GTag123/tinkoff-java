package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @ParameterizedTest
    @CsvSource({
        "4666, 4",
        "123, 3",
        "0, 1",
        "1, 1",
        "-123, 3",
    })
    @DisplayName("Test countDigits")
    public void testCountDigits(long number, int expected) {
        assertThat(Task2.countDigits(number)).isEqualTo(expected);
    }
}
