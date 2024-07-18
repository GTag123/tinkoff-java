package edu.hw6.Task6Ports;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Ports {
    private static final int FIRST_SCANNED_PORT = 0;
    private static final int LAST_SCANNED_PORT = 49151;
    private static final String LOG_FORMAT = "%-10s %-8s %s%n";
    private static final Map<Integer, String> PORT_MAP = Map.ofEntries(
        Map.entry(5000, "Synology DSM (HTTP)"),
        Map.entry(5353, "Multicast DNS (MDNS)"),
        Map.entry(7000, "Azureus BitTorrent")
    );

    private Ports() {}

    public static void scanPorts() {
        log.info(LOG_FORMAT.formatted("Протокол", "Порт", "Сервис"));
        for (int port = FIRST_SCANNED_PORT; port < LAST_SCANNED_PORT; port++) {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                // trying to register port
            } catch (IOException e) {
                log.info(LOG_FORMAT.formatted("TCP", port, (PORT_MAP.get(port) != null ? PORT_MAP.get(port) : "")));
            }
            try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
                // trying to register port
            } catch (IOException e) {
                log.info(LOG_FORMAT.formatted("UDP", port, (PORT_MAP.get(port) != null ? PORT_MAP.get(port) : "")));
            }
        }
    }
}
