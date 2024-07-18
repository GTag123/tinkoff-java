package edu.hw8;

import edu.hw8.Task1RudeServer.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    private static final int PORT = 12345;
    private static final String HOST = "localhost";
    private static final int TIMEOUT = 1000;

    private static Thread serverThread;

    @BeforeAll
    static void setUp() {
        serverThread = new Thread(Server::serverResponse);
        serverThread.start();
    }

    @AfterAll
    static void tearDown() {
        serverThread.interrupt();
    }

    @Test
    void testServerWithValidKeyword() throws IOException {
        String keyword = "оскорбления";
        String expectedResponse = "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами";

        try (Socket socket = new Socket(HOST, PORT);
             OutputStream outputStream = socket.getOutputStream()) {

            outputStream.write(keyword.getBytes(StandardCharsets.UTF_8));
            socket.shutdownOutput();

            Thread.sleep(TIMEOUT);

            String actualResponse = readFromSocket(socket);

            assertEquals(expectedResponse, actualResponse.trim());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testServerWithInvalidKeyword() throws IOException {
        String keyword = "unknown";
        String expectedResponse = "Keyword didn't find";

        try (Socket socket = new Socket(HOST, PORT);
             OutputStream outputStream = socket.getOutputStream()) {

            outputStream.write(keyword.getBytes(StandardCharsets.UTF_8));
            socket.shutdownOutput();

            Thread.sleep(TIMEOUT);

            String actualResponse = readFromSocket(socket);

            assertEquals(expectedResponse, actualResponse.trim());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String readFromSocket(Socket socket) throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        }
        return result.toString();
    }
}
