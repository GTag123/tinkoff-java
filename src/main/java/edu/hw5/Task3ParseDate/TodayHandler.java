package edu.hw5.Task3ParseDate;

import java.time.LocalDate;
import java.util.Optional;

public class TodayHandler extends Handler {
    public TodayHandler(Handler successor) {
        super(successor);
    }

    @Override
    public Optional<LocalDate> parse(String string) {
        if (string.equalsIgnoreCase("today")) {
            return Optional.of(LocalDate.now());
        } else {
            return successor.parse(string);
        }
    }
}
