package edu.hw5.Task3ParseDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Pattern;

public class NumericDateHandler extends Handler {
    public NumericDateHandler(Handler successor) {
        super(successor);
    }

    // 2020-10-10
    private static final Pattern FIRST_REG_EXP = Pattern.compile(
        "^\\d{4}-((0[1-9])|(1[0-2]))-(((0[1-9])|([1-2][0-9]))|(3[0-1]))$");

    // 2020-12-2
    private static final Pattern SECOND_REG_EXP = Pattern.compile(
        "^\\d{4}-(((0[1-9])|([1-2][0-9]))|(3[0-1]))-((0[1-9])|(1[0-2]))$");

    // 1/3/20
    private static final Pattern THIRD_REG_EXP = Pattern.compile(
        "^((([1-2]\\d)|(3[0-1]))|(\\d))/((\\d)|(1[0-2]))/(([0-1][0-9])|(2[0-3]))$");

    // 1/3/1976
    private static final Pattern FOURTH_REG_EXP = Pattern.compile(
        "^((([1-2]\\d)|(3[0-1]))|(\\d))/|((\\d)|(1[0-2]))/\\d{4}$");


    @Override
    public Optional<LocalDate> parse(String string) {
        Optional<LocalDate> res;
        if (FIRST_REG_EXP.matcher(string).find()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(string, formatter);
            res = Optional.of(date);
        } else if (SECOND_REG_EXP.matcher(string).find()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
            LocalDate date = LocalDate.parse(string, formatter);
            res = Optional.of(date);
        } else if (THIRD_REG_EXP.matcher(string).find()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yy");
            LocalDate date = LocalDate.parse(string, formatter);
            res = Optional.of(date);
        } else if (FOURTH_REG_EXP.matcher(string).find()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate date = LocalDate.parse(string, formatter);
            res = Optional.of(date);
        } else {
            return successor.parse(string);
        }
        return res;
    }
}
