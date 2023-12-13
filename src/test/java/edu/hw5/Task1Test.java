package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task1Test {
    private Task1Test() {}

    private static Stream<Arguments> helperTestMethod() {
        return Stream.of(
            Arguments.of(List.of("2022-03-12, 20:20 - 2022-03-12, 23:50",
        "2022-04-01, 21:30 - 2022-04-02, 01:20"), "3 hours 40 minutes"),
            Arguments.of(List.of("2022-03-12, 10:00 - 2022-03-12, 16:30",
                "2022-04-01, 17:00 - 2022-04-01, 17:45", "2022-04-06, 23:15 - 2022-04-07, 03:30"),
                "3 hours 50 minutes"),
            Arguments.of(List.of("2022-03-12, 15:00 - 2022-03-12, 19:55",
                    "2022-04-01, 21:00 - 2022-04-02, 00:00", "2022-04-06, 14:45 - 2022-04-06, 23:30"),
                "5 hours 33 minutes")
        );
    }

    @ParameterizedTest
    @MethodSource("helperTestMethod")
    @DisplayName("Basic test timeOnAverageInOneSession function")
    public void testTimeOnAverageInOneSession(List<String> strings, String expected) {
        assertThat(Task1ComputerClub.timeOnAverageInOneSession(strings)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Test timeOnAverageInOneSession function by null and empty source")
    public void testTimeOnAverageInOneSessionByNull(List<String> list) {
        assertThatThrownBy(() -> Task1ComputerClub.timeOnAverageInOneSession(list)).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<List<String>> helperTestMethodOfWrongInput() {
        return Stream.of(
            List.of("202203-12, 20:20 - 2022-03-12, 23:50",
                "2022-04-01, 21:30 - 2022-04-02, 01:20"),
            List.of("2022-03-12, 10:00 - 2022-03-12, 16:30",
                    "2022-04-01, 17:00-2022-04-01, 17:45", "2022-04-06, 23:15 - 2022-04-07, 03:30"),
            List.of("2022-03-12, 15:00 - 2022-03-12, 19:55",
                    "2022-04-01, 21:00 - 2022-04-02, 00:00", "2022-04-06, 53:45 - 2022-04-06, 23:30"),
            List.of("2022-03-12, 20:20 - 2022-03-12, 23:50",
                "2022-04-01, 21:30 - 2022-04-02, 01:97"),
            List.of("2022-03-12, 20:20 - 2022-03-16, 23:50",
                "2022-04-01, 21:30 - 2022-04-02, 01:25")
        );
    }

    @ParameterizedTest
    @MethodSource("helperTestMethodOfWrongInput")
    @DisplayName("Test timeOnAverageInOneSession function by invalid input")
    public void testTimeOnAverageInOneSessionByWrongInput(List<String> list) {
        assertThatThrownBy(() -> Task1ComputerClub.timeOnAverageInOneSession(list)).isInstanceOf(IllegalArgumentException.class);
    }
}
