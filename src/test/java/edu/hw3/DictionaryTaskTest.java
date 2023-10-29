package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class DictionaryTaskTest {
    private DictionaryTaskTest() {}

    private static Stream<Arguments> getArguments() {
        return Stream.of(
            Arguments.of(List.of("a", "bb", "a", "bb"), Map.of("bb", 2, "a", 2)),
            Arguments.of(List.of("this", "and", "that", "and"), Map.of("that", 1, "and", 2, "this", 1)),
            Arguments.of(List.of("код", "код", "код", "bug"), Map.of("код", 3, "bug", 1)),
            Arguments.of(List.of(1, 1, 2, 2), Map.of(1, 2, 2, 2)),
            Arguments.of(List.of('R', 'e', 'n', 'a', 'n', 'a', 'n', 'a'),
                Map.of('R', 1, 'e', 1, 'n', 3, 'a', 3))
        );
    }


    @ParameterizedTest
    @MethodSource("getArguments")
    @DisplayName("Test basic freqDict")
    public <T> void testBasicFreqDict(List<T> testList, Map<T, Integer> expected) {
        assertThat(DictionaryTask.freqDict(testList)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Null and empty test for freqDict function")
    public <T> void testEmptyFreqDict(List<T> testList) {
        assertThatThrownBy(() -> DictionaryTask.freqDict(testList)).isInstanceOf(IllegalArgumentException.class);
    }
}
