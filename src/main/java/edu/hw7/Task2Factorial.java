package edu.hw7;

import java.util.ArrayList;
import java.util.List;

public class Task2Factorial {
    private Task2Factorial() {}

    public static int factorial(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("Input data must be greater than -1");
        }
        if (num == 0) {
            return 1;
        }

        List<Integer> numArray = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            numArray.add(i);
        }

        return numArray.parallelStream().reduce((first, second) -> first * second).orElse(0);
    }
}
