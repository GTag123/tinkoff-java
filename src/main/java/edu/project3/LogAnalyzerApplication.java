package edu.project3;

import edu.project3.Collectors.BasicInformationCollector;
import edu.project3.Collectors.LogStatsCollector;
import edu.project3.Collectors.RemoteAddressesCollector;
import edu.project3.Collectors.RequestTypesCollector;
import edu.project3.Collectors.RequestedResourcesCollector;
import edu.project3.Collectors.RequestsCollector;
import edu.project3.Filters.DateLogFilter;
import edu.project3.Filters.LogFilter;
import edu.project3.Formatters.AsciiDocLogStatsFormatter;
import edu.project3.Formatters.LogStatsFormatter;
import edu.project3.Formatters.MarkdownLogStatsFormatter;
import edu.project3.Printers.CLIPrinter;
import edu.project3.Printers.Printer;
import edu.project3.Retrievers.LocalRetrieverSelector;
import edu.project3.Retrievers.LogRetriever;
import edu.project3.Retrievers.RetrieverSelector;
import edu.project3.Retrievers.UrlRetrieverSelector;
import edu.project3.utils.Argument;
import edu.project3.utils.FormatType;
import edu.project3.utils.LogData;
import edu.project3.utils.LogSourceWrapper;
import edu.project3.utils.TimeInterval;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogAnalyzerApplication {

    private final List<Argument> arguments;
    private final Printer printer;
    private List<LogStatsCollector> collectors;
    private FormatType formatType = FormatType.MARKDOWN;
    private LogSourceWrapper logSourceWrapper;
    private TimeInterval timeInterval;
    private LogFilter logFilter;

    public LogAnalyzerApplication(String[] args) {
        this.arguments = new ArgsResolver().resolve(args);
        this.printer = new CLIPrinter();
    }

    public void run() {
        initAllParameters();
        loadAllLogsToWrapper(arguments.get(0).value().split(" "));
        initLogFilter();
        initCollectors();
        printStats();
    }

    private void initAllParameters() {
        TimeInterval.TimeIntervalBuilder timeIntervalBuilder = TimeInterval.builder();
        for (Argument argument : arguments) {
            switch (argument.type()) {
                case FORMAT -> {
                    formatType = FormatType.findByValue(argument.value());
                }
                case TO -> {
                    timeIntervalBuilder.to(argument.value());
                }
                case FROM -> {
                    timeIntervalBuilder.from(argument.value());
                }
                default -> {
                }
            }
        }
        timeInterval = timeIntervalBuilder.build();
    }

    private void loadAllLogsToWrapper(String[] paths) {
        List<String> lines = new ArrayList<>();
        for (String path : paths) {
            LogRetriever logRetriever = getRetriever(path);
            lines.addAll(logRetriever.retrieveLogs());
        }
        logSourceWrapper =
            new LogSourceWrapper(
                new LogData(List.of(paths), timeInterval.from(), timeInterval.to()),
                lines.stream().map(LogParser::parseLog).collect(
                    Collectors.toList())
            );
    }

    private LogRetriever getRetriever(String path) {
        RetrieverSelector selector = RetrieverSelector.link(new LocalRetrieverSelector(), new UrlRetrieverSelector());
        return selector.selectRetriever(path);
    }

    private void initLogFilter() {
        logFilter = LogFilter.link(new DateLogFilter(timeInterval.from(), timeInterval.to()));
    }

    private void initCollectors() {
        collectors = new ArrayList<>();
        collectors.add(new BasicInformationCollector(logFilter));
        collectors.add(new RequestsCollector(logFilter));
        collectors.add(new RequestedResourcesCollector(logFilter));
        collectors.add(new RemoteAddressesCollector(logFilter));
        collectors.add(new RequestTypesCollector(logFilter));
    }

    private void printStats() {
        LogStatsFormatter formatter = switch (formatType) {
            case ADOC -> new AsciiDocLogStatsFormatter();
            default -> new MarkdownLogStatsFormatter();
        };
        for (LogStatsCollector collector : collectors) {
            printer.print(formatter.format(collector.collect(logSourceWrapper)));
        }
    }
}
