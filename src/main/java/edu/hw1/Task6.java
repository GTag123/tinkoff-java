package edu.hw1;

import java.util.Arrays;

public class Task6 {
    private Task6() {
    }

    @SuppressWarnings("MagicNumber")
    public static int countK(int n) {
        if (n < 1000 || n > 9999) {
            return -1;
        }
        return countKRecursive(n, 0);
    }

    @SuppressWarnings("MagicNumber")
    private static int countKRecursive(int number, int steps) {
        int n = number;

        if (n == 6174 || steps >= 7) {
            return steps;
        }

        int[] digits = new int[4];
        for (int i = 3; i >= 0; i--) {
            digits[i] = n % 10;
            n /= 10;
        }
        Arrays.sort(digits);

        int ascending = digits[0] * 1000 + digits[1] * 100 + digits[2] * 10 + digits[3];
        int descending = digits[3] * 1000 + digits[2] * 100 + digits[1] * 10 + digits[0];

        int difference = descending - ascending;
        return countKRecursive(difference, steps + 1);
    }
}
