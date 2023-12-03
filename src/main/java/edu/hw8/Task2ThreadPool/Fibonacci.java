package edu.hw8.Task2ThreadPool;

public class Fibonacci {
    private Fibonacci() {}

    public static int getFibonacci(int n) {
        if (n == 1 || n == 0) {
            return n;
        }
        return getFibonacci(n - 1) + getFibonacci(n - 2);
    }
}
