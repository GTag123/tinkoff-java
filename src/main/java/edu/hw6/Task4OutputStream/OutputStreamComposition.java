package edu.hw6.Task4OutputStream;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public final class OutputStreamComposition {
    private OutputStreamComposition() {}

    public static void writeWithComposition(Path path) {
        try (OutputStream outputStream = Files.newOutputStream(path)) {
            CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
            OutputStreamWriter outputStreamWriter =
                new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
            PrintWriter printWriter = new PrintWriter(outputStreamWriter);
            printWriter.write("Programming is learned by writing programs. ― Brian Kernighan");
            printWriter.flush();
            checkedOutputStream.close();
            bufferedOutputStream.close();
            outputStream.close();
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
