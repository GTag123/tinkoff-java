package edu.hw3;

public class RomanNumerals {
    private RomanNumerals() {}

    private static final int MAX_NUMBER = 3999;
    private static final int MIN_NUMBER = 1;

    public static String convertToRoman(int num) {
        if (num < MIN_NUMBER || num > MAX_NUMBER) {
            throw new IllegalArgumentException("Number should be >= 1 and <= 3999");
        }

        int numCopy = num;
        StringBuilder resStr = new StringBuilder();
        while (numCopy > 0) {
            for (RomanNums temp : RomanNums.values()) {
                while (numCopy >= temp.getArabNum()) {
                    resStr.append(temp);
                    numCopy -= temp.getArabNum();
                }
            }
        }
        return resStr.toString();
    }

    private enum RomanNums {
        M(1000),
        CM(900),
        D(500),
        CD(400),
        C(100),
        XC(90),
        L(50),
        XL(40),
        X(10),
        IX(9),
        V(5),
        IV(4),
        I(1);

        private final int arabNum;

        RomanNums(int arabNum) { // constructor of the enum class
            this.arabNum = arabNum;
        }

        public int getArabNum() { // method
            return arabNum;
        }
    }
}
