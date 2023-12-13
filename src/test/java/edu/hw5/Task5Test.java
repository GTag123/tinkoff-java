package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task5Test {
    private Task5Test() {}

    private static Stream<Arguments> helperTestMethod() {
        return Stream.of(
            Arguments.of("А123ВЕ777", true),
            Arguments.of("О777ОО177", true),
            Arguments.of("E777TC77", true),
            Arguments.of("123АВЕ777", false),
            Arguments.of("А123ВГ77", false),
            Arguments.of("А123ВЕ7777", false),
            Arguments.of("R123ВЕ777", false),
            Arguments.of("A888TTP777", false)
            );
    }

    @ParameterizedTest
    @MethodSource("helperTestMethod")
    @DisplayName("Basic test isCarNumberValid function")
    public void testIsCarNumberValid(String num, boolean expected) {
        assertThat(Task5CarNumberCheck.isCarNumberValid(num)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Test isCarNumberValid function by null and empty source")
    public void testIsCarNumberValidByNull(String num) {
        assertThatThrownBy(() -> Task5CarNumberCheck.isCarNumberValid(num)).isInstanceOf(IllegalArgumentException.class);
    }
}
