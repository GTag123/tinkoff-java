package edu.hw8.Task2ThreadPool;

public interface ThreadPool extends AutoCloseable {

    void start();

    void execute(Runnable runnable);
}
