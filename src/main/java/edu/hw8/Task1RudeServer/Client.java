package edu.hw8.Task1RudeServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import static edu.hw8.Task1RudeServer.Server.KEYWORD_RESPONSE_MAP;

public class Client implements Runnable {
    private final Socket clientSocket;

    private static final int SIZE = 1024;

    public Client(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream()) {

            ByteBuffer buffer = ByteBuffer.allocate(SIZE);
            int bytesRead = inputStream.read(buffer.array());

            if (bytesRead > 0) {
                String request = new String(buffer.array(), 0, bytesRead, StandardCharsets.UTF_8);
                String response = KEYWORD_RESPONSE_MAP.getOrDefault(request, "Keyword didn't find");
                outputStream.write(response.getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
