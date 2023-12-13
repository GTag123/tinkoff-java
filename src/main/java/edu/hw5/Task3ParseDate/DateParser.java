package edu.hw5.Task3ParseDate;

import java.time.LocalDate;
import java.util.Optional;

public class DateParser {
    private Handler handler;

    public DateParser() {
        this.handler = new TomorrowHandler(new TodayHandler(new YesterdayHandler(new NumericDateHandler(null))));
    }

    public Optional<LocalDate> parseDate(String string) {
        return handler.parse(string);
    }
}
