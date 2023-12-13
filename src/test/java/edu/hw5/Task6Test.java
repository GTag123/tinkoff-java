package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task6Test {
    private Task6Test() {}

    private static Stream<Arguments> helperTestMethod() {
        return Stream.of(
            Arguments.of("achfdbaabgabcaabg", "abc", true),
            Arguments.of("Hello, world. How are you?", "o, world. Ho", true),
            Arguments.of("renanana", "na", true),
            Arguments.of("JFHoihfjein", "ksd", false),
            Arguments.of("djf33h2xi", "32", false)
        );
    }

    @ParameterizedTest
    @MethodSource("helperTestMethod")
    @DisplayName("Basic test isThereSubsequence function")
    public void testIsThereSubsequence(String S, String T, boolean expected) {
        assertThat(Task6Subsequence.isThereSubsequence(S, T)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Test 1 isThereSubsequence function by null and empty source")
    public void test1IsThereSubsequenceBySecNull(String testSequence) {
        assertThatThrownBy(() -> Task6Subsequence.isThereSubsequence(testSequence, "abc")).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Test 2 isThereSubsequence function by null and empty source")
    public void test2IsThereSubsequenceBySecNull(String testSubsequence) {
        assertThatThrownBy(() -> Task6Subsequence.isThereSubsequence(
            "achfdbaabgabcaabg", testSubsequence)).isInstanceOf(IllegalArgumentException.class);
    }
}
