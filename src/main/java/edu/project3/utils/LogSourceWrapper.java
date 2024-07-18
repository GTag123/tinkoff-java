package edu.project3.utils;

import java.util.List;

public record LogSourceWrapper(LogData logData, List<NginxLog> logs) {

}
