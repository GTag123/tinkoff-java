package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.rmi.registry.RegistryHandler;
import java.util.Arrays;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task2Test {
    private Task2Test() {}

    private static Stream<Arguments> getBasicArguments() {
        return Stream.of(
            Arguments.of("()()()", new String[]{"()", "()", "()"}),
            Arguments.of("((()))", new String[]{"((()))"}),
            Arguments.of("((()))(())()()(()())", new String[]{"((()))", "(())", "()", "()", "(()())"}),
            Arguments.of("((())())(()(()()))", new String[]{"((())())", "(()(()()))"})
        );
    }

    @ParameterizedTest
    @MethodSource("getBasicArguments")
    @DisplayName("Test clusterize function")
    public void testBasicClusterize(String str, String[] expected) {
        assertThat(Task2.clusterize(str).toArray()).isEqualTo(Arrays.stream(expected).toArray());
    }

    @ParameterizedTest
    @CsvSource(value = {
        "(())())",
        ")()",
        "aa()",
        "((])",
        "(((*))"
    })
    @DisplayName("Test clusterize function with wrong content")
    public void testWrongClusterize(String str) {
        /* () -> Task2.clusterize(str) - это lambda-function calls the method Task2.clusterize(str).
           In this case lambda-function don't have any arguments.
         */
        assertThatThrownBy(() -> Task2.clusterize(str)).isInstanceOf(IllegalArgumentException.class);
    }

    @NullAndEmptySource
    @ParameterizedTest
    @DisplayName("Test clusterize function with null and empty content")
    public void testEmptyClusterize(String str) {
        assertThatThrownBy(() -> Task2.clusterize(str)).isInstanceOf(IllegalArgumentException.class);
    }

}
