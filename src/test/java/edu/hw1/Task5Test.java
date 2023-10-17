package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {
    @ParameterizedTest
    @ValueSource(ints = {11211230, 13001120, 23336014, 11})
    @DisplayName("Test isPalindromeDescendant true")
    public void testPalindromeDescendantTrue(int number) {
        assertThat(Task5.isPalindromeDescendant(number)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, 1112, 123456})
    @DisplayName("Test isPalindromeDescendant false")
    public void testPalindromeDescendantFalse(int number) {
        assertThat(Task5.isPalindromeDescendant(number)).isFalse();
    }
}
