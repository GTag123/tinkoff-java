package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6Subsequence {
    private Task6Subsequence() {}

    public static boolean isThereSubsequence(String s, String t) {
        if (s == null || s.isEmpty() || t == null || t.isEmpty()) {
            throw new IllegalArgumentException("Empty strings");
        }
        String subTemp = Pattern.quote(t);
        Pattern subSeq = Pattern.compile(subTemp);
        Matcher matcher = subSeq.matcher(s);
        return matcher.find();
    }
}
