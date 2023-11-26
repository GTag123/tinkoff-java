package edu.hw7.Task3PersonDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynchronizedPersonDatabase implements PersonDatabase {
    private static final String ERROR_MESSAGE = "Input data mustn't be empty";

    private final Map<Integer, Person> peopleByID = new HashMap<>();
    private final Map<String, List<Integer>> nameIndex = new HashMap<>(); // List<Integer>> - list of the ID
    private final Map<String, List<Integer>> addressIndex = new HashMap<>();
    private final Map<String, Integer> phoneIndex = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        if (person == null
                || (person.id() == 0 && person.name().isEmpty()
                && person.address().isEmpty() && person.phoneNumber().isEmpty())) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        peopleByID.put(person.id(), person);

        addByNameOrAddress(nameIndex, person.name(), person.id());
        addByNameOrAddress(addressIndex, person.address(), person.id());

        phoneIndex.put(person.phoneNumber(), person.id());
    }

    private void addByNameOrAddress(Map<String, List<Integer>> map, String key, Integer id) {
        if (map.get(key) == null) {
            List<Integer> idList = new ArrayList<>();
            idList.add(id);
            map.put(key, idList);
        } else {
            map.get(key).add(id);
        }
    }

    @Override
    public synchronized void delete(int id) {
        Person person = peopleByID.get(id);
        if (person == null) {
            throw new IllegalArgumentException("Person with this id wasn't found");
        }

        deleteByNameOrAddress(nameIndex, person.name(), id);
        deleteByNameOrAddress(addressIndex, person.address(), id);

        phoneIndex.remove(person.phoneNumber());

        peopleByID.remove(id);
    }

    private void deleteByNameOrAddress(Map<String, List<Integer>> map, String key, Integer id) {
        if (map.get(key).size() > 1) {
            map.get(key).remove(id);
        } else if (map.get(key).size() == 1) {
            map.remove(key);
        } else {
            throw new RuntimeException("Something went wrong");
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return findByNameOrAddress(nameIndex, name);
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return findByNameOrAddress(addressIndex, address);
    }

    private List<Person> findByNameOrAddress(Map<String, List<Integer>> map, String key) {
        if (key == null && key.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }

        List<Integer> tempList = map.get(key);
        List<Person> res = new ArrayList<>();

        for (Integer personID : tempList) {
            res.add(peopleByID.get(personID));
        }
        return res;
    }

    @Override
    public synchronized Person findByPhone(String phone) {
        if (phone == null && phone.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }

        Integer personID = phoneIndex.get(phone);

        return peopleByID.get(personID);
    }

    // for test
    public synchronized int size() {
        return peopleByID.size();
    }
}
