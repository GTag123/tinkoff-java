package edu.hw3;

import edu.hw3.ContactList.ContactList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class ContactListTest {
    private ContactListTest() {}

    private static Stream<Arguments> getArguments() {
        return Stream.of(
            Arguments.of(new String[]{"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"},
                "ASC", List.of("Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke")),
            Arguments.of(new String[]{"Paul Erdos", "Leonhard Euler", "Carl Gauss"}, "DESC",
                List.of("Carl Gauss", "Leonhard Euler", "Paul Erdos")),
            Arguments.of(new String[]{"Paul Erdos", "Leonhard", "Carl Gauss"}, "DESC",
                List.of("Leonhard", "Carl Gauss", "Paul Erdos")),
            Arguments.of(new String[]{""}, "ASC", List.of(""))
        );
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    @DisplayName("Test basic parseContacts")
    public void testParseContacts(String[] list, String sortName, List<String> expected) {
        assertThat(ContactList.parseContacts(list, sortName)).isEqualTo(expected);
    }
}
