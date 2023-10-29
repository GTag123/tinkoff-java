package edu.hw3;

import java.util.ArrayList;

public class Task2 {
    private Task2() {}

    private static final String ERROR_STR = "Wrong string content";

    public static ArrayList<String> clusterize(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Empty string");
        }

        ArrayList<String> res = new ArrayList<>();

        int i = 0;
        int openBrackets = 0;

        while (i != str.length()) {
            StringBuilder tempStr = new StringBuilder();
            if (str.charAt(i) != '(') {
                throw new IllegalArgumentException(ERROR_STR);
            }

            tempStr.append(str.charAt(i));
            openBrackets++;
            i++;

            while (openBrackets != 0) {
                if (str.charAt(i) == '(') {
                    openBrackets++;
                } else if (str.charAt(i) == ')') {
                    openBrackets--;
                } else {
                    throw new IllegalArgumentException(ERROR_STR);
                }
                tempStr.append(str.charAt(i));
                i++;
            }

            res.add(tempStr.toString());
        }

        return res;
    }


}
