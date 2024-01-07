package edu.project3.Filters;

import edu.project3.utils.NginxLog;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.*;

public class DateLogFilterTest {

    private static Stream<Arguments> inputsForHasPassedFilterTest() {
        return Stream.of(
            Arguments.of(
                new DateLogFilter("2015-01-01", "2016-01-01"),
                NginxLog.builder()
                    .timeLocal(OffsetDateTime.of(LocalDate.of(2015, 1, 2), LocalTime.MIDNIGHT, ZoneOffset.UTC))
                    .build(),
                true
            ),
            Arguments.of(
                new DateLogFilter("2015-01-01", "2016-01-01"),
                NginxLog.builder()
                    .timeLocal(OffsetDateTime.of(LocalDate.of(2016, 1, 2), LocalTime.MIDNIGHT, ZoneOffset.UTC))
                    .build(),
                false
            )
        );
    }

    @ParameterizedTest
    @MethodSource("inputsForHasPassedFilterTest")
    @DisplayName("HasPassedFilter test")
    public void hasPassedFilter_shouldReturnTrue_whenLogPassesTest(
        LogFilter testFilter,
        NginxLog testLog,
        boolean expected
    ) {
        boolean actual = testFilter.hasPassedFilter(testLog);
        assertThat(actual).isEqualTo(expected);
    }
}
