package edu.hw3;

import edu.hw3.TreeMapComparator.TreeMapComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

public class TreeMapComparatorTest {
    private TreeMapComparatorTest() {}

    @Test
    @ValueSource
    @DisplayName("Add null as key test")
    void testAddingNullAsKey() {
    TreeMap<String, String> tree = new TreeMap<>(new TreeMapComparator<String>());
    tree.put("AAA", "bbb");
    tree.put(null, "test");
    assertThat(tree.containsKey(null)).isTrue();
    }
}
