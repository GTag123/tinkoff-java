package edu.hw2.Task3.connection_manager;

import edu.hw2.Task3.connection.Connection;
import edu.hw2.Task3.connection.FaultyConnection;
import edu.hw2.Task3.connection.StableConnection;
import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private static final int CHANCE_OF_FAULTY_CONNECTION = 3;
    private final Random random;

    public DefaultConnectionManager(Random random) {
        this.random = random;
    }

    @Override
    public Connection getConnection() {
        if (random.nextInt(CHANCE_OF_FAULTY_CONNECTION) == 0) {
            return new FaultyConnection(random);
        }

        return new StableConnection();
    }
}
