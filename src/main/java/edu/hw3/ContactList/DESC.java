package edu.hw3.ContactList;

import java.util.PriorityQueue;

public class DESC {
    private DESC() {}

    public static PriorityQueue<String> descendingSort(String[] list) {
        PriorityQueue<String> res = new PriorityQueue<>(new ContactsComparator().reversed());
        for (int i = 0; i < list.length; i++) {
            res.add(list[i]);
        }
        return res;
    }
}
