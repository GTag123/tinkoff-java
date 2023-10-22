package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

class GameDictionary implements Dictionary {
    private static final String[] WORDS = {"my", "hello", "world", "java", "hangman", "programming", "tagir"};
    private final Random random = new Random();

    @Override
    public @NotNull String randomWord() {
        return WORDS[random.nextInt(WORDS.length)];
    }
}
