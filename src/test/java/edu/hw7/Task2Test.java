package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.*;

public class Task2Test {
    private Task2Test() {}

    @ParameterizedTest
    @CsvSource(value = {
        "0, 1",
        "1, 1",
        "2, 2",
        "3, 6",
        "4, 24",
        "5, 120",
        "6, 720",
        "7, 5040",
        "8, 40320"
    })
    @DisplayName("Test factorial function")
    public void testFactorial(int num, int expected) {
        assertThat(Task2Factorial.factorial(num)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test factorial function")
    public void testFactorialByNull() {
        assertThatThrownBy(() -> Task2Factorial.factorial(-7)).isInstanceOf(IllegalArgumentException.class);
    }
}
