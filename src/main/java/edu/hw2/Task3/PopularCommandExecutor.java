package edu.hw2.Task3;

import edu.hw2.Task3.connection.Connection;
import edu.hw2.Task3.connection_exception.ConnectionException;
import edu.hw2.Task3.connection_manager.ConnectionManager;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        if (manager == null) {
            throw new NullPointerException();
        }
        if (maxAttempts < 1) {
            throw new IllegalArgumentException("Please enter pisitive number maxAttempts");
        }

        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) {
        int attempt = 0;
        while (attempt < maxAttempts) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                break;
            } catch (ConnectionException e) {
                attempt++;
                if (attempt >= maxAttempts) {
                    throw new ConnectionException("Tried over maxAttempts", e);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
