package edu.hw8;

import edu.hw8.Task2ThreadPool.Fibonacci;
import edu.hw8.Task2ThreadPool.FixedThreadPool;
import edu.hw8.Task2ThreadPool.ThreadPool;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    @Test
    @SneakyThrows
    @DisplayName("Basic test Thread Pool")
    public void testThreadPool() {
        ThreadPool threadPool = FixedThreadPool.create(4);
        List<Integer> expected = List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55);
        final List<Integer> actual = new CopyOnWriteArrayList<>();
        for (int i = 0; i <= 10; i++) {
            final int cur = i;
            threadPool.execute(() -> {
                actual.add(Fibonacci.getFibonacci(cur));
            });
        }
        threadPool.close();
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}
