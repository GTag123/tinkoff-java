package edu.hw6;

import edu.hw6.Task4OutputStream.OutputStreamComposition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.*;

public class Task4Test {
    private Task4Test() {}

    @Test
    @DisplayName("Test writeWithComposition test")
    public void writeWithComposition_shouldWriteMessageToFileUsingOutputStreamComposition(@TempDir Path testDir) {
        Path testFile = testDir.resolve("test.txt");
        OutputStreamComposition.writeWithComposition(testFile);
        assertThat(testFile).hasContent("Programming is learned by writing programs. ― Brian Kernighan");
    }
}
