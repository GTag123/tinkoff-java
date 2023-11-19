package edu.hw6.Task3AbstractFilter;

import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Pattern;

public final class FilterUtils {
    private FilterUtils() {}

    public static final AbstractFilter REGULAR_FILE = Files::isRegularFile;
    public static final AbstractFilter READABLE = Files::isReadable;

    public static AbstractFilter largerThan(int size) {
        return path -> Files.size(path) >= size;
    }

    public static AbstractFilter globMatches(String glob) {
        return path -> path.getParent().getFileSystem().getPathMatcher("glob:" + glob)
            .matches(path.getFileName());
    }

    public static AbstractFilter regexContains(String regExpString) {
        Pattern regExp = Pattern.compile(regExpString);
        return path -> regExp.matcher(path.toString()).find();
    }

    // магические начальные идентификаторы
    public static AbstractFilter magicNumber(byte... magicBytes) {
        return path -> {
            try {
                byte[] fileBytes = Files.readAllBytes(path);
                if (fileBytes.length < magicBytes.length) {
                    return false;
                }

                for (int i = 0; i < magicBytes.length; i++) {
                    if (fileBytes[i] != magicBytes[i]) {
                        return false;
                    }
                }

                return true;
            } catch (IOException e) {
                return false;
            }
        };
    }
}
