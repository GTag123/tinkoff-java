package edu.hw5;

import edu.hw5.Task3ParseDate.DateParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    public Task3Test() {}

    private static Stream<Arguments> helperTestMethod() {
        return Stream.of(
            Arguments.of("2020-12-02", Optional.of(LocalDate.of(2020, 12, 2))),
            Arguments.of("1925-31-03", Optional.of(LocalDate.of(1925, 3, 31))),
            Arguments.of("1/3/1976", Optional.of(LocalDate.of(1976, 3, 1))),
            Arguments.of("1/3/20", Optional.of(LocalDate.of(2020, 3, 1))),
            Arguments.of("31/12/17", Optional.of(LocalDate.of(2017, 12, 31))),
            Arguments.of("tomorrow", Optional.of(LocalDate.of(2023, 11, 13))),
            Arguments.of("today", Optional.of(LocalDate.of(2023, 11, 12))),
            Arguments.of("yesterday", Optional.of(LocalDate.of(2023, 11, 11))),
            Arguments.of("1 day ago", Optional.of(LocalDate.of(2023, 11, 11))),
            Arguments.of("2234 days ago", Optional.of(LocalDate.of(2017, 9, 30)))
        );
    }

    @ParameterizedTest
    @MethodSource("helperTestMethod")
    @DisplayName("Test parseDate function")
    public void testParseDate(String data, Optional<LocalDate> expected) {
        DateParser dateParser = new DateParser();
        assertThat(dateParser.parseDate(data)).isEqualTo(expected);
    }
}
