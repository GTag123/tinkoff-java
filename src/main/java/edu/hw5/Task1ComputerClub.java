package edu.hw5;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1ComputerClub {
    private Task1ComputerClub() {}

    private static final String ERROR_TEST = "Wrong input data";

    // 2022-03-12, 20:20 - 2022-03-12, 23:50
    private static final Pattern DATA_REG_EXP = Pattern.compile(
        "^(\\d{4})-((0[1-9])|(1[0-2]))-(([0-2][1-9])|(3[0-1])), ((([0-1][0-9])|(2[0-3])):([0-5][0-9]))$");

    @SuppressWarnings("MagicNumber")
    public static String timeOnAverageInOneSession(List<String> data) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Empty array");
        }

        int sessionCount = data.size();
        long sumOfTime = 0;

        for (String datum : data) {
            String[] tempArr = datum.split(" - ");
            if (tempArr.length != 2) {
                throw new IllegalArgumentException(ERROR_TEST);
            }

            Matcher match1 = DATA_REG_EXP.matcher(tempArr[0]);
            Matcher match2 = DATA_REG_EXP.matcher(tempArr[1]);

            if (match1.find() && match2.find()) {
                if (Integer.parseInt(match2.group(6)) - Integer.parseInt(match1.group(6)) > 1) {
                    throw new IllegalArgumentException(ERROR_TEST);
                }

                String timeString1 = match1.group(8);
                String timeString2 = match2.group(8);

                // java.time
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime time1 = LocalTime.parse(timeString1, formatter);
                LocalTime time2 = LocalTime.parse(timeString2, formatter);

                if (time1.isBefore(time2)) {
                    // don't work with java.util
                    Duration duration = Duration.between(time1, time2);
                    sumOfTime += duration.toMinutes();
                } else {
                    Duration duration = Duration.between(time2, time1);
                    sumOfTime += 1440 - duration.toMinutes();
                }
            } else {
                throw new IllegalArgumentException(ERROR_TEST);
            }
        }

        long averageTime = sumOfTime / sessionCount;
        return averageTime / 60 + " hours " + averageTime % 60 + " minutes";
    }
}
