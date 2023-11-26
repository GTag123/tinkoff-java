package edu.hw7.Task4MonteCarlo;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MonteCarlo extends AbstractMonteCarlo {
    @Override
    public double getPI(long simulations) {
        double circleCount = 0;
        Random random = ThreadLocalRandom.current();
        for (int i = 0; i < simulations; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            if (isInCircle(x, y)) {
                circleCount++;
            }
        }
        return MONTE_CARLO_CONST * (circleCount / (double) simulations);
    }
}
