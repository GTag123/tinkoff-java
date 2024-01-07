package edu.project3.Retrievers;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class LocalLogRetrieverTest {

    @Test
    @DisplayName("RetrieveLogsLocal test")
    public void retrieveLogs_shouldReturnListWhichContainsAllLinesOfLogFile() {
        List<String> lines = new LocalLogRetriever("logs/test/logs.txt").retrieveLogs();
        assertThat(lines).hasSize(51462);
    }
}
