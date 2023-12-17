package edu.hw10.Task2.PalindromeChecker;

import org.apache.commons.lang3.StringUtils;

public class PalindromeCheckerImpl implements PalindromeChecker {

    @Override
    public boolean isPalindrome(String line) {
        return line.equals(StringUtils.reverse(line));
    }
}
