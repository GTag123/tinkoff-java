package edu.project3.Collectors;

import edu.project3.Filters.LogFilter;
import edu.project3.utils.FormatterComponent;
import edu.project3.utils.LogSourceWrapper;
import edu.project3.utils.NginxLog;
import edu.project3.utils.Request;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RequestedResourcesCollector extends LogStatsCollector {

    private static final int RESOURCES_LIMIT = 10;

    public RequestedResourcesCollector(LogFilter logFilter) {
        super(logFilter);
    }

    @Override
    public FormatterComponent collect(LogSourceWrapper logWrapper) {
        return FormatterComponent.builder()
            .header("%d самых часто запрашиваемых ресурсов".formatted(RESOURCES_LIMIT))
            .tableHeaders(List.of("Ресурс", "Количество запросов"))
            .lines(getStatsLines(logWrapper))
            .build();
    }

    @Override
    protected List<String> getStatsLines(LogSourceWrapper logWrapper) {
        return logWrapper.logs().stream()
            .filter(log -> logFilter.hasPassedFilter(log))
            .map(NginxLog::request)
            .map(Request::resource)
            .map(str -> str.substring(str.lastIndexOf('/')))
            .collect(Collectors.collectingAndThen(
                Collectors.groupingBy(Function.identity(), Collectors.counting()),
                map -> map.entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .map(entry -> "'" + entry.getKey() + "'" + "|" + entry.getValue())
                    .limit(RESOURCES_LIMIT)
                    .toList()
            ));
    }
}
