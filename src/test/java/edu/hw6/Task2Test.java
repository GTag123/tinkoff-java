package edu.hw6;

import edu.hw6.Task2CloneFile.CloneFile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.*;

public class Task2Test {
    private Task2Test() {}
    @TempDir
    private Path testDir;

    @Test
    @DisplayName("Test cloneFile function")
    public void testCloneFile() throws IOException {
        Path originalFile = testDir.resolve("testName.txt");
        Files.write(originalFile, "Hello, world!".getBytes());

        CloneFile.cloneFile(originalFile);
        CloneFile.cloneFile(originalFile);
        CloneFile.cloneFile(originalFile);

        assertThat(testDir).isDirectoryContaining(
            path -> path.getFileName().toString().endsWith("testName — копия.txt")
        );
        assertThat(testDir).isDirectoryContaining(
            path -> path.getFileName().toString().endsWith("testName — копия (2).txt")
        );
        assertThat(testDir).isDirectoryContaining(
            path -> path.getFileName().toString().endsWith("testName — копия (3).txt")
        );

    }
}
