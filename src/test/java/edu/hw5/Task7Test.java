package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task7Test {
    private Task7Test() {}

    @ParameterizedTest
    @CsvSource(value = {
        "110101, true",
        "000, true",
        "&^03m=A+92%#1, false",
        "0011111, false",
        "11, false"
    })
    @DisplayName("Basic test containsAtLeast3Char function")
    public void testContainsAtLeast3Char(String string, boolean expected) {
        assertThat(Task7BinaryRegExp.containsAtLeast3Char(string)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Test containsAtLeast3Char function by null and empty source")
    public void testContainsAtLeast3CharByNull(String string) {
        assertThatThrownBy(() -> Task7BinaryRegExp.containsAtLeast3Char(string)).isInstanceOf(IllegalArgumentException.class);
    }


    @ParameterizedTest
    @CsvSource(value = {
        "110101, true",
        "000, true",
        "11, true",
        "0011111, false",
        "1^03m=A+92%#1, false"
    })
    @DisplayName("Basic test startsAndEndsSameChar function")
    public void testStartsAndEndsSameChar(String string, boolean expected) {
        assertThat(Task7BinaryRegExp.startsAndEndsSameChar(string)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Test startsAndEndsSameChar function by null and empty source")
    public void testStartsAndEndsSameCharByNull(String string) {
        assertThatThrownBy(() -> Task7BinaryRegExp.startsAndEndsSameChar(string)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "010, true",
        "1, true",
        "11, true",
        "110101, false",
        "%#1, false"
    })
    @DisplayName("Basic test containsFrom1To3Char function")
    public void testContainsFrom1To3Char(String string, boolean expected) {
        assertThat(Task7BinaryRegExp.containsFrom1To3Char(string)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Test containsFrom1To3Char function by null and empty source")
    public void testContainsFrom1To3CharByNull(String string) {
        assertThatThrownBy(() -> Task7BinaryRegExp.containsFrom1To3Char(string)).isInstanceOf(IllegalArgumentException.class);
    }
}
