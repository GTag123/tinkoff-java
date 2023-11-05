package edu.project1;

public class Main {
    private Main() {
    }

    private final static String CONFIG_PATH = "src/main/java/edu/project1/config.properties";

    public static void main(String[] args) {
        Configs.getInstance().load(CONFIG_PATH);
        String[] words = Configs.getInstance().getProperty("words").split(",");
        new ConsoleHangman(new GameDictionary(words)).play();
    }
}
