package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task8Bonus {
    private Task8Bonus() {}

    private static final String ERROR_TEXT = "Empty string";

    private static final Pattern ODD_LENGTH_REG_EXP = Pattern.compile("^([01]{2})*[01]$");

    private static final Pattern ODD_OR_EVEN_LENGTH_REG_EXP = Pattern.compile("^0(?:[01]{2})*$|^1[01]([01]{2})*$");

    private static final Pattern NUM_OF_ZEROS_IS_3_REG_EXP = Pattern.compile("^1*01*01*01*$");

    private static final Pattern ALMOST_ANY_STRING_REG_EXP = Pattern.compile("^(?!((11$)|(111$)))[01]*$");

    private static final Pattern ODD_CHAR_1_REG_EXP = Pattern.compile("^(1[01])*1?$");

    private static final Pattern CONSIST_2_ZEROS_AND_1_UNIT = Pattern.compile("^(((0*010+)|(100+))|(0+01))$");

    private static final Pattern NO_CONSECUTIVE_1_REG_EXP = Pattern.compile("^1?(01?)*0*$");

    public static boolean oddLengthString(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException(ERROR_TEXT);
        }
        Matcher matcher = ODD_LENGTH_REG_EXP.matcher(string);
        return matcher.find();
    }

    public static boolean oddOrEvenLength(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException(ERROR_TEXT);
        }
        Matcher matcher = ODD_OR_EVEN_LENGTH_REG_EXP.matcher(string);
        return matcher.find();
    }

    public static boolean numberOfZerosIs3(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException(ERROR_TEXT);
        }
        Matcher matcher = NUM_OF_ZEROS_IS_3_REG_EXP.matcher(string);
        return matcher.find();
    }

    public static boolean almostAnyString(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException(ERROR_TEXT);
        }
        Matcher matcher = ALMOST_ANY_STRING_REG_EXP.matcher(string);
        return matcher.find();
    }

    public static boolean oddCharOne(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException(ERROR_TEXT);
        }
        Matcher matcher = ODD_CHAR_1_REG_EXP.matcher(string);
        return matcher.find();
    }

    public static boolean contains2ZerosAnd1Unit(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException(ERROR_TEXT);
        }
        Matcher matcher = CONSIST_2_ZEROS_AND_1_UNIT.matcher(string);
        return matcher.find();
    }

    public static boolean noConsecutive1(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException(ERROR_TEXT);
        }
        Matcher matcher = NO_CONSECUTIVE_1_REG_EXP.matcher(string);
        return matcher.find();
    }
}
