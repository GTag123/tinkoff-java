package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4PasswordCheck {
    private Task4PasswordCheck() {}

    private static final Pattern PASS_REG = Pattern.compile("[~!@#$%^&*|]");

    public static boolean isPasswordContainsChar(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Empty string");
        }
        Matcher matcher = PASS_REG.matcher(password);
        return matcher.find();
    }
}
