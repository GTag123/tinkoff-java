package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task2Test {
    private Task2Test() {}

    private static Stream<Arguments> helperTestMethod1() {
        return Stream.of(
            Arguments.of(1925,
                List.of(LocalDate.of(1925, 2, 13),
                    LocalDate.of(1925, 3, 13),
                    LocalDate.of(1925, 11, 13))),
            Arguments.of(2024,
                List.of(LocalDate.of(2024, 9, 13),
                    LocalDate.of(2024, 12, 13))),
            Arguments.of(2000,
                List.of(LocalDate.of(2000, 10, 13)))
        );
    }

    @ParameterizedTest
    @MethodSource("helperTestMethod1")
    @DisplayName("Basic test findAllFridayThe13th function")
    public void testFindAllFridayThe13th(int year, List<LocalDate> expected) {
        assertThat(Task2FridayThe13th.findAllFridayThe13th(year)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test findAllFridayThe13th function by invalid source")
    public void testFindAllFridayThe13thByInvalidSource() {
        assertThatThrownBy(() -> Task2FridayThe13th.findAllFridayThe13th(0)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Task2FridayThe13th.findAllFridayThe13th(-2023)).isInstanceOf(IllegalArgumentException.class);
    }


    private static Stream<Arguments> helperTestMethod2() {
        return Stream.of(
            Arguments.of(LocalDate.of(1969, 6, 13),
                LocalDate.of(1970, 2, 13)),
            Arguments.of(LocalDate.of(2017, 1, 13),
                LocalDate.of(2017, 10, 13)),
            Arguments.of(LocalDate.of(2004, 2, 13),
                LocalDate.of(2004, 8, 13))
        );
    }

    @ParameterizedTest
    @MethodSource("helperTestMethod2")
    @DisplayName("Basic test getNextFridayThe13th function")
    public void testGetNextFridayThe13th(LocalDate friday, LocalDate expected) {
        assertThat(Task2FridayThe13th.getNextFridayThe13th(friday)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test getNextFridayThe13th function by null and empty source")
    public void testGetNextFridayThe13thByNull() {
        assertThatThrownBy(() -> Task2FridayThe13th.getNextFridayThe13th(null)).isInstanceOf(IllegalArgumentException.class);
    }
}
