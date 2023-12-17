package edu.hw10.Task2.FibCalculator;

import edu.hw10.Task2.Cache;

public interface FibCalculator {

    @Cache
    long getFib(int number);
}
