package edu.hw6;

import edu.hw6.Task1DiskMap.DiskMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.*;

public class Task1Test {
    private Task1Test() {}

    /*
       @TempDir - для создания временной директории в тестовом методе.
       Эта временная директория будет автоматически удалена после выполнения теста.
     */
    @TempDir(cleanup = CleanupMode.ALWAYS)
    private Path testDir;

    private Path storage;
    private DiskMap diskMap;


    @BeforeEach
    public void initStorage() {
        storage = testDir.resolve("data_storage.txt");
        diskMap = new DiskMap(storage);
    }


    @Test
    @DisplayName("Test put function")
    public void testPut() {
        diskMap.put("key", "value");
        assertThat(storage).hasContent("key:value");
    }

    @Test
    @DisplayName("Test size function")
    public void testSize() {
        diskMap.put("test1", "easy");
        diskMap.put("test2", "normal");
        diskMap.put("test3", "hard");
        assertThat(diskMap.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Test isEmpty function with true result")
    public void test1IsEmpty() {
        assertThat(diskMap.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Test isEmpty function with false result")
    public void test2IsEmpty() {
        diskMap.put("A", "B");
        assertThat(diskMap.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Test containsKey function with true result")
    public void test1ContainsKey() {
        String testKey = "Reu3";
        diskMap.put("Reu1", "Tag1");
        diskMap.put("Reu2", "Tag2");
        diskMap.put(testKey, "Tag3");
        assertThat(diskMap.containsKey(testKey)).isTrue();
    }

    @Test
    @DisplayName("Test containsKey function with false result")
    public void test2ContainsKey() {
        String testKey = "Reu4";
        diskMap.put("Reu1", "Tag1");
        diskMap.put("Reu2", "Tag2");
        diskMap.put("Reu3", "Tag3");
        assertThat(diskMap.containsKey(testKey)).isFalse();
    }

    @Test
    @DisplayName("Test containsValue function with true result")
    public void test1ContainsValue() {
        String testValue = "Tag2";
        diskMap.put("Reu1", "Tag1");
        diskMap.put("Reu2", testValue);
        diskMap.put("Reu3", "Tag3");
        assertThat(diskMap.containsValue(testValue)).isTrue();
    }

    @Test
    @DisplayName("Test containsValue function with false result")
    public void test2ContainsValue() {
        String testValue = "Tag4";
        diskMap.put("Reu1", "Tag1");
        diskMap.put("Reu2", "Tag2");
        diskMap.put("Reu3", "Tag3");
        assertThat(diskMap.containsValue(testValue)).isFalse();
    }

    @Test
    @DisplayName("Test get function")
    public void testGet() {
        String key = "Happy";
        String value = "New Year";
        diskMap.put("Merry", "Christmas");
        diskMap.put(key, value);
        assertThat(diskMap.get(key)).isEqualTo(value);
    }

    @Test
    @DisplayName("Test remove function")
    public void testRemove() {
        String key = "Reu2";
        diskMap.put("Reu1", "Tag1");
        diskMap.put(key, "Tag2");
        diskMap.put("Reu3", "Tag3");
        diskMap.remove(key);
        assertThat(storage).hasContent("Reu1:Tag1\nReu3:Tag3");
    }

    @Test
    @DisplayName("Test putAll function")
    public void testPutAll() {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("key1", "value1");
        testMap.put("key2", "value2");
        diskMap.putAll(testMap);
        assertThat(storage).hasContent("key1:value1\nkey2:value2");
    }

    @Test
    @DisplayName("Test clear function")
    public void testClear() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        diskMap.clear();
        assertThat(storage).isEmptyFile();
    }

    @Test
    @DisplayName("Test keySet function")
    public void testKeySet() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        diskMap.put("key3", "value3");

        Set<String> expected = new HashSet<>();
        expected.add("key1");
        expected.add("key2");
        expected.add("key3");

        assertThat(diskMap.keySet()).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test values function")
    public void testValues() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        diskMap.put("key3", "value3");

        Set<String> expected = new HashSet<>();
        expected.add("value1");
        expected.add("value2");
        expected.add("value3");

        assertThat(diskMap.values()).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test entrySet function")
    public void testEntrySet() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        diskMap.put("key3", "value3");

        Collection<Map.Entry<String, String>> expected = new HashSet<>();
        expected.add(Map.entry("key1", "value1"));
        expected.add(Map.entry("key2", "value2"));
        expected.add(Map.entry("key3", "value3"));

        assertThat(diskMap.entrySet()).isEqualTo(expected);
    }
}
