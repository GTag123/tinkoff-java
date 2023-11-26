package edu.hw7.Task4MonteCarlo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import lombok.SneakyThrows;

public class ParallelMonteCarlo extends AbstractMonteCarlo {
    private static final int NUMBER_OF_THREADS = 8;

    @Override
    @SneakyThrows
    public double getPI(long simulations) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        // Каждый элемент массива будет хранить результат выполнения одного из потоков.
        Future<Long>[] threadResults = new Future[NUMBER_OF_THREADS];

        /*  Реализован в виде счетчика, который уменьшается по мере завершения каждого потока,
            и другие потоки могут ждать, пока счетчик не достигнет нуля.
         */
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_THREADS);

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            final int threadIndex = i;
            // Разделяем общее количество симуляций между потоками
            long threadSimulations = simulations / NUMBER_OF_THREADS;
            // Submit используется для отправки задачи на выполнение в пул потоков.
            threadResults[i] = executorService.submit(() -> {
                long circleCount = getCircleCount(threadSimulations);
                latch.countDown();
                return circleCount;
            });
        }
        double totalCircleCount = 0;

        // Остановка приема новых задач и завершение выполнения потоков в пуле после завершения всех задач.
        executorService.shutdown();

        // Ожидание завершения всех потоков (данный поток будет блокирован до тех пор, пока счетчик не достигнет нуля).
        latch.await();
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            totalCircleCount += threadResults[i].get();
        }
        return MONTE_CARLO_CONST * (totalCircleCount / simulations);
    }

    private long getCircleCount(long simulations) {
        long circleCount = 0;
        Random random = ThreadLocalRandom.current();
        for (int j = 0; j < simulations; j++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            if (isInCircle(x, y)) {
                circleCount++;
            }
        }
        return circleCount;
    }
}
