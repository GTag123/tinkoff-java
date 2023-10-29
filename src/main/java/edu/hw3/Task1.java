package edu.hw3;

public class Task1 {
    private Task1() {}

    private static final int ASSCI_A = 65;
    private static final int ASSCI_Z = 90;
    private static final int ASSCI_LOWER_A = 97;
    private static final int ASSCI_LOWER_Z = 122;

    private static final int INTERVAL = 25;

    public static String atbash(String oldStr) {
        if (oldStr == null || oldStr.isEmpty()) {
            throw new IllegalArgumentException("Empty string");
        }

        int i = 0;
        StringBuilder newStr = new StringBuilder();
        while (i != oldStr.length()) {
            int assciCode = (int) oldStr.charAt(i);
            if (assciCode >= ASSCI_A && assciCode <= ASSCI_Z) {
                newStr.append(getMirrorUpperLetter(oldStr.charAt(i)));
            } else if (assciCode >= ASSCI_LOWER_A && assciCode <= ASSCI_LOWER_Z) {
                newStr.append(getMirrorLowerLetter(oldStr.charAt(i)));
            } else {
                newStr.append(oldStr.charAt(i));
            }
            i++;
        }
        return newStr.toString();
    }

    private static char getMirrorLowerLetter(char letter) {
        int interval = (int) letter - ASSCI_LOWER_A;
        return (char) (ASSCI_LOWER_Z - interval);
    }

    private static char getMirrorUpperLetter(char letter) {
        int interval = (int) letter - ASSCI_A;
        return (char) (ASSCI_Z - interval);
    }
}
