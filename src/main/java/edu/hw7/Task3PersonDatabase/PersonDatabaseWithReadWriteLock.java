package edu.hw7.Task3PersonDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PersonDatabaseWithReadWriteLock implements PersonDatabase {
    private static final String ERROR_MESSAGE = "Input data mustn't be empty";

    private final Map<Integer, Person> peopleByID = new HashMap<>();
    private final Map<String, List<Integer>> nameIndex = new HashMap<>();
    private final Map<String, List<Integer>> addressIndex = new HashMap<>();
    private final Map<String, Integer> phoneIndex = new HashMap<>();

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        readWriteLock.writeLock().lock();
        try {
            if (person == null
                    || (person.id() == 0 && person.name().isEmpty()
                    && person.address().isEmpty() && person.phoneNumber().isEmpty())) {
                throw new IllegalArgumentException(ERROR_MESSAGE);
            }
            peopleByID.put(person.id(), person);

            addByNameOrAddress(nameIndex, person.name(), person.id());
            addByNameOrAddress(addressIndex, person.address(), person.id());

            phoneIndex.put(person.phoneNumber(), person.id());
        } finally {
            readWriteLock.writeLock().unlock();
        }
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
    public void delete(int id) {
        readWriteLock.writeLock().lock();
        try {
            Person person = peopleByID.get(id);
            if (person == null) {
                throw new IllegalArgumentException("Person with this id wasn't found");
            }

            deleteByNameOrAddress(nameIndex, person.name(), id);
            deleteByNameOrAddress(addressIndex, person.address(), id);

            phoneIndex.remove(person.phoneNumber());

            peopleByID.remove(id);
        } finally {
            readWriteLock.writeLock().unlock();
        }
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
    public List<Person> findByName(String name) {
        readWriteLock.readLock().lock();
        try {
            return findByNameOrAddress(nameIndex, name);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        readWriteLock.readLock().lock();
        try {
            return findByNameOrAddress(addressIndex, address);
        } finally {
            readWriteLock.readLock().unlock();
        }
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
    public Person findByPhone(String phone) {
        readWriteLock.readLock().lock();
        try {
            if (phone == null && phone.isEmpty()) {
                throw new IllegalArgumentException(ERROR_MESSAGE);
            }

            Integer personID = phoneIndex.get(phone);

            return peopleByID.get(personID);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    // for test
    public int size() {
        readWriteLock.readLock().lock();
        try {
            return peopleByID.size();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
