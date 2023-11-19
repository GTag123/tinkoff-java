package edu.project3.Collectors;

import edu.project3.Filters.DateLogFilter;
import edu.project3.Filters.LogFilter;
import edu.project3.utils.FormatterComponent;
import edu.project3.utils.LogData;
import edu.project3.utils.LogSourceWrapper;
import edu.project3.utils.NginxLog;
import edu.project3.utils.Response;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.*;

public class RequestsCollectorTest {

    private static Stream<Arguments> inputsForCollectTest() {
        return Stream.of(
            Arguments.of(
                new LogSourceWrapper(
                    new LogData(List.of("testSource")),
                    List.of(
                        NginxLog.builder().response(Response.builder().statusCode(200).build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().response(Response.builder().statusCode(200).build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().response(Response.builder().statusCode(200).build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().response(Response.builder().statusCode(404).build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().response(Response.builder().statusCode(404).build()).timeLocal(OffsetDateTime.now()).build()
                    )
                ), List.of("200|OK|3", "404|Not Found|2")
            ));
    }

    @ParameterizedTest
    @MethodSource("inputsForCollectTest")
    @DisplayName("RequestCollect test")
    public void collect_shouldReturnRequestsStats(LogSourceWrapper testLogs, List<String> expectedLines) {
        FormatterComponent actual = new RequestsCollector(LogFilter.link(new DateLogFilter(null, null))).collect(testLogs);
        assertThat(actual.lines()).containsExactlyInAnyOrderElementsOf(expectedLines);
    }
}
