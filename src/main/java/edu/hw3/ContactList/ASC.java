package edu.hw3.ContactList;

import java.util.PriorityQueue;

public class ASC {
    private ASC() {}

    public static PriorityQueue<String> ascendingSort(String[] list) {
        PriorityQueue<String> res = new PriorityQueue<>(new ContactsComparator());
        for (int i = 0; i < list.length; i++) {
            res.add(list[i]);
        }
        return res;
    }
}
