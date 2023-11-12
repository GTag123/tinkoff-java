package edu.hw5.Task3ParseDate;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YesterdayHandler extends Handler {
    public YesterdayHandler(Handler successor) {
        super(successor);
    }

    private static final Pattern SOME_DAYS_AGO_REG_EXP = Pattern.compile("^([1-9]([0-9]*)) days ago$");

    @Override
    public Optional<LocalDate> parse(String string) {
        Matcher matcher = SOME_DAYS_AGO_REG_EXP.matcher(string);
        if (string.equalsIgnoreCase("yesterday")
            || string.equalsIgnoreCase("1 day ago")) {
                return Optional.of(LocalDate.now().minusDays(1));
        } else if (matcher.find()) {
            int n = Integer.parseInt(matcher.group(1));
            return Optional.of(LocalDate.now().minusDays(n));
        } else {
            return successor.parse(string);
        }
    }
}
