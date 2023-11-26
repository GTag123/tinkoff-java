package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1Counter {
    private final AtomicInteger counter;

    public Task1Counter() {
        counter = new AtomicInteger(0);
    }

    public int incrementByTwoThreads(int limit) {
        Thread incrementor1 = new Thread(() -> {
            for (int i = 0; i < limit; i++) {
                counter.addAndGet(1);
            }
        });
        Thread incrementor2 = new Thread(() -> {
            for (int i = 0; i < limit; i++) {
                counter.addAndGet(1);
            }
        });

        incrementor1.start();
        incrementor2.start();

        try {
            incrementor1.join();
            incrementor2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return counter.get();
    }
}
