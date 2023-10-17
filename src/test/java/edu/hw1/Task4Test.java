package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    @ParameterizedTest
    @CsvSource({
        "123456, 214365",
        "hTsii  s aimex dpus rtni.g, This is a mixed up string.",
        "badce, abcde",
    })
    @DisplayName("Test fix string")
    public void testFixString(String str, String expected) {
        assertThat(Task4.fixString(str)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test null in fixstring")
    public void testFixStringNull() {
        assertThat(Task4.fixString(null)).isNull();
    }
}
