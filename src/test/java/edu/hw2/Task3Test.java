package edu.hw2;

import edu.hw2.Task3.PopularCommandExecutor;
import edu.hw2.Task3.connection.FaultyConnection;
import edu.hw2.Task3.connection.StableConnection;
import edu.hw2.Task3.connection_exception.ConnectionException;
import edu.hw2.Task3.connection_manager.DefaultConnectionManager;
import edu.hw2.Task3.connection_manager.FaultyConnectionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task3Test {
    @Test
    @DisplayName("Test StableConnection in DefaultConnectionManager")
    void testDefaultConnectionManagerWithStableConnection() {
        var manager = new DefaultConnectionManager(new Random(2));
        var connection = manager.getConnection();
        assertThat(connection).isInstanceOf(StableConnection.class);
    }

    @Test
    @DisplayName("Test FaultyConnection in DefaultConnectionManager")
    void testDefaultConnectionManagerWithFaultyConnection() {
        var manager = new DefaultConnectionManager(new Random(1));
        var connection = manager.getConnection();
        assertThat(connection).isInstanceOf(FaultyConnection.class);
    }

    @Test
    @DisplayName("Test fail in FaultyConnectionManager")
    void testAlwaysFaultyConnection() {
        var manager = new FaultyConnectionManager(new Random(4));
        var executor = new PopularCommandExecutor(manager, 5);
        assertThrows(ConnectionException.class, executor::updatePackages);
    }

    @Test
    @DisplayName("Test success in DefaultConnectionManager")
    void testAlwaysStableConnection() {
        var manager = new DefaultConnectionManager(new Random(2));
        var executor = new PopularCommandExecutor(manager, 5);
        assertDoesNotThrow(executor::updatePackages);
    }
}
