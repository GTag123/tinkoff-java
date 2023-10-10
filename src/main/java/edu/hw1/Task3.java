package edu.hw1;

import java.util.Arrays;

public class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length == 0) {
            return false;
        }
        if (arr2 == null || arr2.length == 0) {
            return false;
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return arr1[0] > arr2[0] && arr1[arr1.length - 1] < arr2[arr2.length - 1];
    }
}
