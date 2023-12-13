package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task5CarNumberCheck {
    private Task5CarNumberCheck() {}

    private static final Pattern CORRECT_CAR_NUM =
        Pattern.compile("^[АВЕКМНОРСТУХABEKMHOPCTYX]{1}\\d{3}[АВЕКМНОРСТУХABEKMHOPCTYX]{2}\\d{2,3}$");

    public static boolean isCarNumberValid(String carNumber) {
        if (carNumber == null || carNumber.isEmpty()) {
            throw new IllegalArgumentException("Empty string");
        }
        Matcher matcher = CORRECT_CAR_NUM.matcher(carNumber);
        return matcher.find();
    }
}
