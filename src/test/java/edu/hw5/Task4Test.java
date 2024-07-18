package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task4Test {
    private Task4Test() {}

    @ParameterizedTest
    @CsvSource(value = {
        "aabbb$klmn",
        "LanaDeleRay@mail.com",
        "Studants can not do this *@#$%^| task"
    })
    @DisplayName("Basic first test isPasswordContainsChar function")
    public void test1IsPasswordContainsChar(String password) {
        assertThat(Task4PasswordCheck.isPasswordContainsChar(password)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {
        "aslkjwadmkv",
        "AAAA",
        "ALksmSNJ"
    })
    @DisplayName("Basic second test isPasswordContainsChar function")
    public void test2IsPasswordContainsChar(String password) {
        assertThat(Task4PasswordCheck.isPasswordContainsChar(password)).isFalse();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Test isPasswordContainsChar function by null and empty source")
    public void testIsPasswordContainsCharByNull(String password) {
        assertThatThrownBy(() -> Task4PasswordCheck.isPasswordContainsChar(password)).isInstanceOf(IllegalArgumentException.class);
    }
}
