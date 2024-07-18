package edu.hw5.Task3ParseDate;

import java.time.LocalDate;
import java.util.Optional;

public class TomorrowHandler extends Handler {
    public TomorrowHandler(Handler successor) {
        super(successor);
    }

    @Override
    public Optional<LocalDate> parse(String string) {
        if (string.equalsIgnoreCase("tomorrow")) {
            return Optional.of(LocalDate.now().plusDays(1));
        } else {
            return successor.parse(string);
        }
    }
}
