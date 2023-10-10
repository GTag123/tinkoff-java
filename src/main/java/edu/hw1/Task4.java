package edu.hw1;

public class Task4 {
    private Task4() {
    }

    public static String fixString(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i += 2) {
            if (i + 1 != str.length()) {
                sb.append(str.charAt(i + 1));
            }
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
}
