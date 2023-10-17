package edu.hw1;

public class Task1 {
    private static final int SECONDS_IN_MINUTE = 60;

    private Task1() {
    }

    public static long minutesToSeconds(String time) { // "mm:ss" format
        var timeArray = checkAndReturnTime(time);
        if (timeArray == null) {
            return -1;
        }

        long minutes;
        long seconds;
        try {
            minutes = Long.parseLong(timeArray[0]);
            seconds = Long.parseLong(timeArray[1]);
        } catch (NumberFormatException e) {
            return -1;
        }
        if (seconds >= SECONDS_IN_MINUTE) {
            return -1;
        }
        return minutes * SECONDS_IN_MINUTE + seconds;
    }

    private static String[] checkAndReturnTime(String time) {
        if (time == null) {
            return null;
        }

        String[] timeArray = time.split(":");

        if (timeArray.length != 2) {
            return null;
        }
        return timeArray;
    }
}
