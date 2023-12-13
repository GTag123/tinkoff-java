package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2FridayThe13th {
    private Task2FridayThe13th() {}

    @SuppressWarnings("MagicNumber")
    public static List<LocalDate> findAllFridayThe13th(int year) {
        if (year <= 0) {
            throw new IllegalArgumentException("Year must be greater than 0");
        }

        List<LocalDate> fridayThe13ths = new ArrayList<>();

        for (int month = 1; month <= 12; month++) {
            LocalDate currantDate = LocalDate.of(year, month, 13);
            if (currantDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridayThe13ths.add(currantDate);
            }
        }
        return fridayThe13ths;
    }

    @SuppressWarnings("MagicNumber")
    public static LocalDate getNextFridayThe13th(LocalDate fromDate) {
        if (fromDate == null) {
            throw new IllegalArgumentException("Empty source");
        }
        LocalDate nextFriday13th = fromDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

        while (nextFriday13th.getDayOfMonth() != 13) {
            nextFriday13th = nextFriday13th.plusWeeks(1);
        }

        return nextFriday13th;
    }
}

