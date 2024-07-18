package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task7BinaryRegExp {
    private Task7BinaryRegExp() {}

    private static final String ERROR_TEXT = "Empty string";
    private static final Pattern FIRST_REG_EXP = Pattern.compile("[01]{2}0[01]*");
    private static final Pattern THIRD_REG_EXP = Pattern.compile("^[01]{1,3}$");

    public static boolean containsAtLeast3Char(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException(ERROR_TEXT);
        }
        Matcher matcher = FIRST_REG_EXP.matcher(string);
        return matcher.find();
    }

    public static boolean startsAndEndsSameChar(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException(ERROR_TEXT);
        }
        String startWith = string.substring(0, 1);
        final Pattern secondRegExp = Pattern.compile("^" + startWith + "[01]*" + startWith + "$");
        Matcher matcher = secondRegExp.matcher(string);
        return matcher.find();
    }

    public static boolean containsFrom1To3Char(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException(ERROR_TEXT);
        }
        Matcher matcher = THIRD_REG_EXP.matcher(string);
        return matcher.find();
    }
}
