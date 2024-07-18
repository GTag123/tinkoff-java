package edu.project3.utils;

import java.util.List;
import lombok.Builder;

@Builder
public record FormatterComponent(String header, List<String> tableHeaders, List<String> lines) {

}
