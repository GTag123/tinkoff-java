package edu.hw1;

public class Task2 {
    private Task2() {
    }

    @SuppressWarnings("MagicNumber")
    public static int countDigits(long number) {
        var num = number;
        if (num == 0) {
            return 1;
        }
        int count = 0;
        while (num != 0) {
            num /= 10;
            ++count;
        }
        return count;
    }
}
