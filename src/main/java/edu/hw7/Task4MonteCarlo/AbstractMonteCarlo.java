package edu.hw7.Task4MonteCarlo;

public abstract class AbstractMonteCarlo {
    protected static final int MONTE_CARLO_CONST = 4;

    public abstract double getPI(long simulations);

    protected boolean isInCircle(double x, double y) {
        return x * x + y * y < 1; // radius = 1
    }
}
