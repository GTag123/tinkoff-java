package edu.hw2.Task3.connection_manager;

import edu.hw2.Task3.connection.Connection;
import edu.hw2.Task3.connection.FaultyConnection;
import java.util.Random;

public class FaultyConnectionManager implements ConnectionManager {
    private final Random random;

    public FaultyConnectionManager(Random random) {
        this.random = random;
    }

    @Override
    public Connection getConnection() {
        return new FaultyConnection(random);
    }
}
