package edu.project3.Collectors;

import edu.project3.Filters.LogFilter;
import edu.project3.utils.FormatterComponent;
import edu.project3.utils.LogSourceWrapper;
import edu.project3.utils.NginxLog;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RemoteAddressesCollector extends LogStatsCollector {

    private static final int ADDRESSES_LIMIT = 10;

    public RemoteAddressesCollector(LogFilter logFilter) {
        super(logFilter);
    }

    @Override
    public FormatterComponent collect(LogSourceWrapper logWrapper) {
        return FormatterComponent.builder()
            .header("%d самых часто запрашиваемых адресов".formatted(ADDRESSES_LIMIT))
            .tableHeaders(List.of("Адрес", "Количество"))
            .lines(getStatsLines(logWrapper))
            .build();
    }

    @Override
    protected List<String> getStatsLines(LogSourceWrapper logWrapper) {
        return logWrapper.logs().stream()
            .filter(log -> logFilter.hasPassedFilter(log))
            .collect(Collectors.collectingAndThen(
                Collectors.groupingBy(NginxLog::remoteAddress, Collectors.counting()),
                map -> map.entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .map(entry -> entry.getKey() + "|" + entry.getValue())
                    .limit(ADDRESSES_LIMIT)
                    .collect(Collectors.toList())
            ));
    }
}
