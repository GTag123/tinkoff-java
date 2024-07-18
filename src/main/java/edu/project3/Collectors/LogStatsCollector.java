package edu.project3.Collectors;

import edu.project3.Filters.LogFilter;
import edu.project3.utils.FormatterComponent;
import edu.project3.utils.LogSourceWrapper;
import java.util.List;

public abstract class LogStatsCollector {

    protected LogFilter logFilter;

    public LogStatsCollector(LogFilter logFilter) {
        this.logFilter = logFilter;
    }

    public abstract FormatterComponent collect(LogSourceWrapper logWrapper);

    protected abstract List<String> getStatsLines(LogSourceWrapper logWrapper);
}
