package edu.hw5.Task3ParseDate;

import java.time.LocalDate;
import java.util.Optional;

public abstract class Handler {
    protected Handler successor;

    public Handler(Handler successor) {
        this.successor = successor;
    }

    public abstract Optional<LocalDate> parse(String string);
}
