package edu.hw8.Task1RudeServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private Server() {}

    private static final int PORT = 12345;
    private static final int MAX_CONNECTIONS = 5;

    public static final ConcurrentHashMap<String, String> KEYWORD_RESPONSE_MAP = new ConcurrentHashMap<>();

    static {
        KEYWORD_RESPONSE_MAP.put("личности", "Не переходи на личности там, где их нет");
        KEYWORD_RESPONSE_MAP.put("оскорбления", "Если твои противники перешли на личные оскорбления,"
            + " будь уверена — твоя победа не за горами");
        KEYWORD_RESPONSE_MAP.put("глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно..."
            + " Ты просто бог идиотизма.");
        KEYWORD_RESPONSE_MAP.put("интеллект", "Чем ниже интеллект, тем громче оскорбления");
    }

    public static void serverResponse() {
        ExecutorService executor = Executors.newFixedThreadPool(MAX_CONNECTIONS);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executor.submit(new Client(clientSocket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }
    }
}
