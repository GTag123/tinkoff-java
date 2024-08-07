package edu.project3.Collectors;

import edu.project3.Filters.LogFilter;
import edu.project3.utils.FormatterComponent;
import edu.project3.utils.HttpStatusCode;
import edu.project3.utils.LogSourceWrapper;
import edu.project3.utils.NginxLog;
import edu.project3.utils.Response;
import java.util.List;
import java.util.stream.Collectors;

public class RequestsCollector extends LogStatsCollector {

    public RequestsCollector(LogFilter logFilter) {
        super(logFilter);
    }

    @Override
    public FormatterComponent collect(LogSourceWrapper logWrapper) {
        return FormatterComponent.builder()
            .header("Коды ответа")
            .tableHeaders(List.of("Код", "Имя", "Количество"))
            .lines(getStatsLines(logWrapper))
            .build();
    }

    @Override
    protected List<String> getStatsLines(LogSourceWrapper logWrapper) {
        return logWrapper.logs().stream()
            .filter(log -> logFilter.hasPassedFilter(log))
            .map(NginxLog::response)
            .collect(
                Collectors.collectingAndThen(
                    Collectors.groupingBy(Response::statusCode, Collectors.counting()),
                    map -> map.entrySet().stream()
                        .map(entry -> HttpStatusCode.getByValue(entry.getKey()) + "|"
                            + entry.getValue())
                        .sorted()
                        .collect(Collectors.toList()).reversed()
            ));
    }
}
