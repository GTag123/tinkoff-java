package edu.project3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LogAnalyzerApplicationTest {

    @Test
    @DisplayName("Start program test")
    public void run_shouldNotThrowAnyException_whenEverythingIsRight() {
        Assertions.assertDoesNotThrow(() -> new LogAnalyzerApplication(new String[] {"--path",
            "files/logs.txt",
            "--format",
            "markdown", "--from", "2015-05-17", "--to", "2023-11-16" }).run());
    }
}
