package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {
    @ParameterizedTest
    @CsvSource({
        "6621, 5",
        "6554, 4",
        "1234, 3",
        "3524, 3",
        "6174, 0",
        "999, -1",
        "12345, -1"
    })
    @DisplayName("Test countK")
    public void testCountK(int number, int expected) {
        assertThat(Task6.countK(number)).isEqualTo(expected);
    }
}
