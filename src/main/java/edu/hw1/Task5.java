package edu.hw1;

public class Task5 {
    private Task5() {
    }

    public static boolean isPalindromeDescendant(int num) {
        String str = Integer.toString(num);
        while (str.length() > 1) {
            if (isPalindrome(str)) {
                return true;
            }
            str = sumAdjacentDigits(str);
        }
        return false;
    }

    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static String sumAdjacentDigits(String str) {
        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < str.length()) {
            int sum = Character.getNumericValue(str.charAt(i));
            i++;

            if (i < str.length()) {
                sum += Character.getNumericValue(str.charAt(i));
                i++;
            }

            result.append(sum);
        }

        return result.toString();
    }
}
