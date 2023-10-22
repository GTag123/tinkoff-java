package edu.project1;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleHangman {
    private ConsoleHangman() {
    }

    private final static Scanner SCANNER = new Scanner(System.in);

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        String word = new GameDictionary().randomWord();
        Session session = new Session(word, Configs.MAX_ATTEMPTS);

        while (true) {
            Printer.printStr("The word: " + String.valueOf(session.getUserAnswer()));
            Printer.printStr("Guess a letter: ");
            try {
                String input = SCANNER.next();

                // Выход из игры при помощи команды
                if (input.equalsIgnoreCase(Configs.EXIT_COMMAND)) {
                    Printer.printStr(Configs.QUIT_MESSAGE);
                    break;
                }

                if (input.length() != 1) {
                    Printer.printStr("Please enter a single letter.");
                    continue;
                }

                char guess = input.charAt(0);
                GuessResult result = session.guess(guess);
                Printer.printState(result);

                if (result instanceof GuessResult.Defeat || result instanceof GuessResult.Win) {
                    break;
                }
            } catch (NoSuchElementException e) {
                // Выход из игры при нажатии Ctrl+D
                Printer.printStr(Configs.QUIT_MESSAGE);
                break;
            }
        }
        SCANNER.close();
    }

}

