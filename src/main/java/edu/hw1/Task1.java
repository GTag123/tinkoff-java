package edu.hw1;

public class Task1 {
    private Task1() {
    }

    @SuppressWarnings("MagicNumber")
    public static long minutesToSeconds(String time) {// "mm:ss" format
        String[] timeArray = time.split(":");
        if (timeArray.length != 2) {
            return -1;
        }
        long minutes, seconds;
        try {
            minutes = Long.parseLong(timeArray[0]);
            seconds = Long.parseLong(timeArray[1]);
        } catch (NumberFormatException e) {
            return -1;
        }
        if (seconds > 59) {
            return -1;
        }
        return minutes * 60 + seconds;
    }
}
