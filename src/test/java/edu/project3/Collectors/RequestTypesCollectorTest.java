package edu.project3.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import edu.project3.Filters.DateLogFilter;
import edu.project3.Filters.LogFilter;
import edu.project3.utils.FormatterComponent;
import edu.project3.utils.LogData;
import edu.project3.utils.LogSourceWrapper;
import edu.project3.utils.NginxLog;
import edu.project3.utils.Request;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RequestTypesCollectorTest {
    private static Stream<Arguments> inputsForCollectTest() {
        return Stream.of(
            Arguments.of(
                new LogSourceWrapper(
                    new LogData(List.of("testSource")),
                    List.of(
                        NginxLog.builder().request(Request.builder().type("GET").build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().request(Request.builder().type("GET").build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().request(Request.builder().type("GET").build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().request(Request.builder().type("PUT").build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().request(Request.builder().type("GET").build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().request(Request.builder().type("DELETE").build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().request(Request.builder().type("DELETE").build()).timeLocal(OffsetDateTime.now()).build(),
                        NginxLog.builder().request(Request.builder().type("PATCH").build()).timeLocal(OffsetDateTime.now()).build()
                    )
                ), List.of("GET|4", "PUT|1", "DELETE|2", "PATCH|1")
            ));
    }

    @ParameterizedTest
    @MethodSource("inputsForCollectTest")
    @DisplayName("RequestTypeCollect test")
    public void collect_shouldReturnRequestsStats(LogSourceWrapper testLogs, List<String> expectedLines) {
        FormatterComponent actual = new RequestTypesCollector(LogFilter.link(new DateLogFilter(null, null))).collect(testLogs);
        assertThat(actual.lines()).containsExactlyInAnyOrderElementsOf(expectedLines);
    }
}
