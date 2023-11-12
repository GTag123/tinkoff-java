package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task8Test {
    private Task8Test() {}

    @ParameterizedTest
    @CsvSource(value = {
        "11010, true",
        "000, true",
        "0011111, true",
        "0, true",
        "&^03m=A+92%#1, false",
        "0011, false",
        "00111110, false"
    })
    @DisplayName("Basic test oddLengthString function")
    public void testOddLengthString(String string, boolean expected) {
        assertThat(Task8Bonus.oddLengthString(string)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Test oddLengthString function by null and empty source")
    public void testOddLengthStringByNull(String string) {
        assertThatThrownBy(() -> Task8Bonus.oddLengthString(string)).isInstanceOf(IllegalArgumentException.class);
    }


    @ParameterizedTest
    @CsvSource(value = {
        "1101, true",
        "100000, true",
        "0011111, true",
        "0, true",
        "&^03m=A+92%#1, false",
        "0011, false",
        "00111110, false"
    })
    @DisplayName("Basic test oddOrEvenLength function")
    public void testOddOrEvenLength(String string, boolean expected) {
        assertThat(Task8Bonus.oddOrEvenLength(string)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Test oddOrEvenLength function by null and empty source")
    public void testOddOrEvenLengthByNull(String string) {
        assertThatThrownBy(() -> Task8Bonus.oddOrEvenLength(string)).isInstanceOf(IllegalArgumentException.class);
    }


    @ParameterizedTest
    @CsvSource(value = {
        "01010, true",
        "000, true",
        "11111111111110111111111010, true",
        "0, false",
        "&^03m=A+92%#1, false",
        "0011, false",
        "000111110, false"
    })
    @DisplayName("Basic test numberOfZerosIs3 function")
    public void testNumberOfZerosIs3(String string, boolean expected) {
        assertThat(Task8Bonus.numberOfZerosIs3(string)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Test numberOfZerosIs3 function by null and empty source")
    public void testNumberOfZerosIs3ByNull(String string) {
        assertThatThrownBy(() -> Task8Bonus.numberOfZerosIs3(string)).isInstanceOf(IllegalArgumentException.class);
    }


    @ParameterizedTest
    @CsvSource(value = {
        "01010, true",
        "000, true",
        "11111111111110111111111010, true",
        "0, true",
        "1, true",
        "0000011000, true",
        "&^03m=A+92%#1, false",
        "11, false",
        "111, false"
    })
    @DisplayName("Basic test almostAnyString function")
    public void testAlmostAnyString(String string, boolean expected) {
        assertThat(Task8Bonus.almostAnyString(string)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Test almostAnyString function by null and empty source")
    public void testAlmostAnyStringByNull(String string) {
        assertThatThrownBy(() -> Task8Bonus.almostAnyString(string)).isInstanceOf(IllegalArgumentException.class);
    }


    @ParameterizedTest
    @CsvSource(value = {
        "101010, true",
        "1, true",
        "10101111101010111011, true",
        "0, false",
        "&^03m=A+92%#1, false",
        "10110, false"
    })
    @DisplayName("Basic test oddChar1 function")
    public void testOddChar1(String string, boolean expected) {
        assertThat(Task8Bonus.oddCharOne(string)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Test oddChar1 function by null and empty source")
    public void testOddChar1ByNull(String string) {
        assertThatThrownBy(() -> Task8Bonus.oddCharOne(string)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1000000, true",
        "001000000, true",
        "001, true",
        "10100, false",
        "000001010, false",
        "&^03m=A+92%#1, false",
        "100110, false"
    })
    @DisplayName("Basic test contains2ZerosAnd1Unit function")
    public void testContains2ZerosAnd1Unit(String string, boolean expected) {
        assertThat(Task8Bonus.contains2ZerosAnd1Unit(string)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Test contains2ZerosAnd1Unit function by null and empty source")
    public void testContains2ZerosAnd1UnitByNull(String string) {
        assertThatThrownBy(() -> Task8Bonus.contains2ZerosAnd1Unit(string)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "101010, true",
        "1, true",
        "0, true",
        "10101111101010111011, false",
        "100100000110, false",
        "&^03m=A+92%#1, false",
        "10110, false"
    })
    @DisplayName("Basic test noConsecutive1 function")
    public void testNoConsecutive1(String string, boolean expected) {
        assertThat(Task8Bonus.noConsecutive1(string)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Test noConsecutive1 function by null and empty source")
    public void testNoConsecutive1ByNull(String string) {
        assertThatThrownBy(() -> Task8Bonus.noConsecutive1(string)).isInstanceOf(IllegalArgumentException.class);
    }
}
