package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Printer {
    private static final Logger LOGGER = LogManager.getLogger();

    private Printer() {
    }

    public static void printStr(String str) {
        LOGGER.info(str);
    }

    public static void printState(GuessResult guess) {
        LOGGER.info(guess.message());
    }
}
