package edu.hw7.Task3Test;

import edu.hw7.Task3PersonDatabase.Person;
import edu.hw7.Task3PersonDatabase.PersonDatabaseWithReadWriteLock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.*;

public class Task3TestReadWritelock {
    private Task3TestReadWritelock() {}

    private PersonDatabaseWithReadWriteLock databaseInit() {
        PersonDatabaseWithReadWriteLock database = new PersonDatabaseWithReadWriteLock();

        var person1 = new Person(1, "John", "123 Main St", "555-1234");
        var person2 = new Person(2, "Alice", "123 Main St", "555-5678");
        var person3 = new Person(3, "Bob", "789 Pine St", "555-9999");
        var person4 = new Person(4, "Ann", "726 Basic St", "555-7259");
        var person5 = new Person(5, "Tom", "961 Apple St", "555-2684");
        var person6 = new Person(6, "Ann", "108 Fresh St", "555-9091");

        database.add(person1);
        database.add(person2);
        database.add(person3);
        database.add(person4);
        database.add(person5);
        database.add(person6);

        return database;
    }

    @Test
    @DisplayName("Test findByName function")
    public void testFindByName() {
        PersonDatabaseWithReadWriteLock database = databaseInit();

        assertThat(database.findByName("Ann")).isEqualTo(Stream.of(
            new Person(4, "Ann", "726 Basic St", "555-7259"),
            new Person(6, "Ann", "108 Fresh St", "555-9091")).toList());
    }

    @Test
    @DisplayName("Test findByAddress function")
    public void testFindByAddress() {
        PersonDatabaseWithReadWriteLock database = databaseInit();

        assertThat(database.findByAddress("123 Main St")).isEqualTo(Stream.of(
            new Person(1, "John", "123 Main St", "555-1234"),
            new Person(2, "Alice", "123 Main St", "555-5678")).toList());
    }

    @Test
    @DisplayName("Test findByPhone function")
    public void testFindByPhone() {
        PersonDatabaseWithReadWriteLock database = databaseInit();

        assertThat(database.findByPhone("555-2684")).isEqualTo(
            new Person(5, "Tom", "961 Apple St", "555-2684"));
    }

    @Test
    @DisplayName("Test delete function")
    public void testDelete() {
        PersonDatabaseWithReadWriteLock database = new PersonDatabaseWithReadWriteLock();

        Person person = new Person(1, "John", "123 Main St", "555-1234");
        database.add(person);

        database.delete(1);

        assertThat(database.size()).isEqualTo(0);
    }
}
