package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class GameDictionary implements Dictionary {
    private final String[] words;
    private final Random random = new Random();

    public GameDictionary(String[] words) {
        this.words = words;
    }

    @Override
    public @NotNull String randomWord() {
        return words[random.nextInt(words.length)];
    }
}
