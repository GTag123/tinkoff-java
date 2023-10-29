package edu.project1;

import java.util.NoSuchElementException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {
    private final static Scanner SCANNER = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger();
    Session session;

    public ConsoleHangman(GameDictionary dict) {
        String word = dict.randomWord();
        int maxAttempts = Integer.parseInt(Configs.getInstance().getProperty("maxAttempts"));
        session = new Session(word, maxAttempts);
    }

    public void play() {
        while (true) {
            LOGGER.info("The word: " + String.valueOf(session.getUserAnswer()));
            LOGGER.info("Guess a letter: ");
            try {
                String input = SCANNER.next();

                // Выход из игры при помощи команды
                if (input.equalsIgnoreCase(Configs.getInstance().getProperty("exitCommand"))) {
                    printQuit();
                    break;
                }

                if (input.length() != 1) {
                    LOGGER.info("Please enter a single letter.");
                    continue;
                }

                char guess = input.charAt(0);
                GuessResult result = session.guess(guess);
                LOGGER.info(result.message());

                if (result instanceof GuessResult.Defeat || result instanceof GuessResult.Win) {
                    break;
                }
            } catch (NoSuchElementException e) {
                // Выход из игры при нажатии Ctrl+D
                printQuit();
                break;
            }
        }
        SCANNER.close();
    }

    private void printQuit() {
        LOGGER.info(Configs.getInstance().getProperty("quitMsg"));
    }

}

