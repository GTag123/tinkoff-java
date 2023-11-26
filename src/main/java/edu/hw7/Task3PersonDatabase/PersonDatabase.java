package edu.hw7.Task3PersonDatabase;

import java.util.List;

public interface PersonDatabase {
    void add(Person person);

    void delete(int id);

    List<Person> findByName(String name);

    List<Person> findByAddress(String address);

    Person findByPhone(String phone);
}
