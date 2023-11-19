package edu.hw6.Task2CloneFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CloneFile {
    private CloneFile() {}

    private static final String CLONE_PART = " — копия";

    public static void cloneFile(Path path) {
//        String fileName = path.getFileName().toString().substring(0, path.toString().indexOf('.'));
//        String fileExtension = path.toString().substring(path.toString().indexOf('.'));

        String fileName = path.getFileName().toString();
        String fileExtension = fileName.substring(fileName.indexOf('.'));
        fileName = fileName.substring(0, fileName.indexOf('.'));

        boolean isFileCreated = false;
        int currentCopy = 1;
        while (!isFileCreated) {
            String newFileName = path.getParent().toString() + '/' + fileName + CLONE_PART
                + (currentCopy == 1 ? "" : " (" + currentCopy + ")")
                + fileExtension;
            if (Files.exists(Path.of(newFileName))) {
                currentCopy++;
            } else {
                try {
                    Files.copy(path, Path.of(newFileName));
                    isFileCreated = true;
                } catch (IOException e) {
                    throw new RuntimeException("File copy error", e);
                }
            }
        }
    }
}
