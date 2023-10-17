package edu.hw1;

public class Task7 {
    private Task7() {
    }

    public static int rotateLeft(int n, int shift) {
        if (shift < 0) {
            return -1;
        }
        int newShift = shift % Integer.toBinaryString(n).length();
        return (n << newShift | n >> (Integer.toBinaryString(n).length() - newShift))
            & ((1 << Integer.toBinaryString(n).length()) - 1);
    }

    public static int rotateRight(int n, int shift) {
        if (shift < 0) {
            return -1;
        }
        var curShift = shift % Integer.SIZE;
        return (n >>> curShift) | (n << (Integer.SIZE - curShift));
    }
}
