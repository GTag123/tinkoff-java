package edu.hw3.ContactList;

import java.util.ArrayList;
import java.util.PriorityQueue;
import static edu.hw3.ContactList.ASC.ascendingSort;
import static edu.hw3.ContactList.DESC.descendingSort;

public class ContactList {
    private ContactList() {}

    public static ArrayList<String> parseContacts(String[] list, String sortName) {
        if (sortName == null || sortName.isEmpty()) {
            throw new IllegalArgumentException("Empty sort name");
        }

        if (list == null || list.length == 0) {
            return new ArrayList<>();
        }

        PriorityQueue<String> q;
        if (sortName.equals("ASC")) {
            q = ascendingSort(list);
        } else if (sortName.equals("DESC")) {
            q = descendingSort(list);
        } else {
            throw new IllegalArgumentException("Wrong sort name");
        }

        ArrayList<String> res = new ArrayList<>();
        int size = q.size();
        for (int i = 0; i < size; i++) {
            res.add(q.poll());
        }

        return res;
    }
}
