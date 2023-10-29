package edu.hw3.ContactList;

import java.util.Comparator;

public class ContactsComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) { // o1 == "David Hume", o2 == "John Locke"
        if (o1 == null || o2 == null || o1.isEmpty() || o2.isEmpty()) {
            throw new IllegalArgumentException("Empty element");
        }

        String[] arr1 = o1.split(" ");
        String[] arr2 = o2.split(" ");

        if (arr1.length == arr2.length) {
            return arr1[arr1.length - 1].compareTo(arr2[arr2.length - 1]);
        } else if (arr1.length == 2 && arr2.length == 1) {
            return arr1[1].compareTo(arr2[0]);
        } else if (arr1.length == 1 && arr2.length == 2) {
            return arr1[0].compareTo(arr2[1]);
        } else {
            throw new IllegalArgumentException("Unsuitable element");
        }
    }
}
