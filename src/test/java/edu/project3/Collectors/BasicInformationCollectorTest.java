package edu.project3.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import edu.project3.Filters.DateLogFilter;
import edu.project3.Filters.LogFilter;
import edu.project3.utils.FormatterComponent;
import edu.project3.utils.LogData;
import edu.project3.utils.LogSourceWrapper;
import edu.project3.utils.NginxLog;
import edu.project3.utils.Response;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BasicInformationCollectorTest {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static Stream<Arguments> inputsForCollectTest() {
        return Stream.of(
            Arguments.of(
                new LogSourceWrapper(
                    new LogData(List.of("test.log")),
                    List.of(
                        NginxLog.builder().response(Response.builder().bodyBytesSend(200).build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().response(Response.builder().bodyBytesSend(400).build()).timeLocal(OffsetDateTime.now()).build()
                    )
                ),
                List.of(
                    "Файл(-ы)|'test.log'",
                    "Начальная дата|-",
                    "Конечная дата|-",
                    "Количество запросов|2",
                    "Средний размер ответа|300.0b"
                )
            ),
            Arguments.of(
                new LogSourceWrapper(
                    new LogData(List.of("test1.log", "test2.log"), OffsetDateTime.now(), OffsetDateTime.now()),
                    List.of(
                        NginxLog.builder().response(Response.builder().bodyBytesSend(200).build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().response(Response.builder().bodyBytesSend(400).build()).timeLocal(OffsetDateTime.now()).build()
                    )
                ),
                List.of(
                    "Файл(-ы)|'test1.log''test2.log'",
                    "Начальная дата|" + DATE_FORMATTER.format(OffsetDateTime.now()),
                    "Конечная дата|" + DATE_FORMATTER.format(OffsetDateTime.now()),
                    "Количество запросов|2",
                    "Средний размер ответа|300.0b"
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("inputsForCollectTest")
    @DisplayName("BasicInformationCollect test")
    public void collect_shouldReturnRequestsStats(LogSourceWrapper testLogs, List<String> expectedLines) {
        FormatterComponent actual = new BasicInformationCollector(LogFilter.link(new DateLogFilter(null, null))).collect(testLogs);
        assertThat(actual.lines()).containsExactlyInAnyOrderElementsOf(expectedLines);
    }
}
