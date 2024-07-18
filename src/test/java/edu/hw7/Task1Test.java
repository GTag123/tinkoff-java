package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.*;

public class Task1Test {
    private Task1Test() {}

    @ParameterizedTest
    @CsvSource(value = {
        "-100, 0",
        "1, 2",
        "100, 200",
        "484, 968",
        "2500, 5000",
        "10_000, 20_000"
    })
    @DisplayName("Test incrementByTwoThreads function")
    public void testIncrementByTwoThreads(int limit, int expected) {
        var testObject = new Task1Counter();
        assertThat(testObject.incrementByTwoThreads(limit)).isEqualTo(expected);
    }
}
