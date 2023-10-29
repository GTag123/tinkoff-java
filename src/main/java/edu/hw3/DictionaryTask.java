package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryTask {
    private DictionaryTask() {}

    public static <T> Map<T, Integer> freqDict(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Empty array");
        }

        Map<T, Integer> resMap = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            if (resMap.get(list.get(i)) == null) { // resMap.putIfAbsent(...)
                resMap.put(list.get(i), 1);
                continue;
            }
            resMap.put(list.get(i), resMap.get(list.get(i)) + 1);
        }

        return resMap;
    }
}
