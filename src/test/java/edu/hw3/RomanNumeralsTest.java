package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class RomanNumeralsTest {
    private RomanNumeralsTest() {}

    @ParameterizedTest
    @CsvSource(value = {
        "2, II",
        "12, XII",
        "16, XVI",
        "9, IX",
        "3999, MMMCMXCIX",
    })
    @DisplayName("Test convertToRoman function")
    public void testConvertToRoman(int num, String expected) {
        assertThat(RomanNumerals.convertToRoman(num)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "0",
        "-1",
        "4500"
    })
    @DisplayName("Wrong input tests for convertToRoman")
    public void testWrongConvertToRoman(int testNumber) {
        assertThatThrownBy(() -> RomanNumerals.convertToRoman(testNumber)).isInstanceOf(IllegalArgumentException.class);
    }
}
