package edu.project1;

import java.util.Arrays;

class Session {
    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts;

    Session(String answer, int maxAttempts) {
        this.answer = answer;
        this.userAnswer = new char[answer.length()];
        Arrays.fill(userAnswer, '*');
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
    }

    public GuessResult guess(char guess) {
        if (attempts >= maxAttempts) {
            return handleIncorrect();
        }

        boolean correctGuess = false;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == guess) {
                userAnswer[i] = guess;
                correctGuess = true;
            }
        }

        if (correctGuess) {
            return handleCorrect();
        } else {
            return handleIncorrect();
        }
    }

    private GuessResult handleCorrect() {
        if (String.valueOf(userAnswer).equals(answer)) {
            return new GuessResult.Win(userAnswer, attempts, maxAttempts);
        } else {
            return new GuessResult.SuccessfulGuess(userAnswer, attempts, maxAttempts);
        }
    }

    private GuessResult handleIncorrect() {
        attempts++;
        if (attempts >= maxAttempts) {
            return new GuessResult.Defeat(userAnswer, attempts, maxAttempts);
        } else {
            return new GuessResult.FailedGuess(userAnswer, attempts, maxAttempts);
        }
    }

    public char[] getUserAnswer() {
        return userAnswer;
    }
}

