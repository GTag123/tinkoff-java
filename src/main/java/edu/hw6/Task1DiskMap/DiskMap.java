package edu.hw6.Task1DiskMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private static final String READ_ERROR_MESSAGE = "File reading error";
    private static final String KEY_ERROR_MESSAGE = "Key must be string";
    private static final String SEPARATOR = ":";

    private final Path path;

    public DiskMap(Path path) {
        this.path = path;
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new RuntimeException("File creation error", e);
        }
    }

    @Override
    public int size() {
        try {
            return Files.readAllLines(path).size(); // each string - unique key-value pair
        } catch (IOException e) {
            throw new RuntimeException(READ_ERROR_MESSAGE, e);
        }
    }

    @Override
    public boolean isEmpty() {
        try {
            return Files.readAllLines(path).isEmpty();
        } catch (IOException e) {
            throw new RuntimeException(READ_ERROR_MESSAGE, e);
        }
    }

    @Override
    public boolean containsKey(Object key) {
        if (!(key instanceof String searchString)) {
            throw new IllegalArgumentException(KEY_ERROR_MESSAGE);
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            while (line != null) {
                if (line.startsWith(searchString)) {
                    return true;
                }
                line = reader.readLine();
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(READ_ERROR_MESSAGE, e);
        }
    }

    @Override
    public boolean containsValue(Object value) {
        if (!(value instanceof String searchString)) {
            throw new IllegalArgumentException(KEY_ERROR_MESSAGE);
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            while (line != null) {
                if (line.endsWith(searchString)) {
                    return true;
                }
                line = reader.readLine();
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(READ_ERROR_MESSAGE, e);
        }
    }

    @Override
    public String get(Object key) {
        if (!(key instanceof String searchKey)) {
            throw new IllegalArgumentException(KEY_ERROR_MESSAGE);
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            while (line != null) {
                if (line.startsWith(searchKey)) {
                    String[] tempArray = line.split(SEPARATOR);
                    return tempArray[1];
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(READ_ERROR_MESSAGE, e);
        }

        return null;
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        String lineToWrite = key + SEPARATOR + value + "\n";
        try {
            Files.writeString(path, lineToWrite, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lineToWrite;
    }

    @Override
    public String remove(Object key) {
        if (!(key instanceof String searchKey)) {
            throw new IllegalArgumentException(KEY_ERROR_MESSAGE);
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            List<String> listOfLines = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                if (!line.startsWith(searchKey)) {
                    listOfLines.add(line);
                }
                line = reader.readLine();
            }

            // StandardOpenOption.TRUNCATE_EXISTING - гарантирует, что файл будет очищен перед записью новых строк.
            Files.write(path, listOfLines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(READ_ERROR_MESSAGE, e);
        }

        return (String) key;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        for (var entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        try {
            Files.writeString(path, "", StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        Set<String> resSet = new HashSet<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            while (line != null) {
                String[] tempString = line.split(SEPARATOR);
                resSet.add(tempString[0]);

                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(READ_ERROR_MESSAGE, e);
        }
        return resSet;
    }

    @NotNull
    @Override
    public Collection<String> values() {
        Set<String> resSet = new HashSet<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            while (line != null) {
                String[] tempString = line.split(SEPARATOR);
                resSet.add(tempString[1]);

                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(READ_ERROR_MESSAGE, e);
        }
        return resSet;
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        Set<Entry<String, String>> resSet = new HashSet<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            while (line != null) {
                String[] tempString = line.split(SEPARATOR);
                resSet.add(Map.entry(tempString[0], tempString[1]));

                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(READ_ERROR_MESSAGE, e);
        }
        return resSet;
    }
}
