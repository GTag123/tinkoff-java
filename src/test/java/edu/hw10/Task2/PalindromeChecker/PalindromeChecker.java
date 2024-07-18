package edu.hw10.Task2.PalindromeChecker;

import edu.hw10.Task2.Cache;

public interface PalindromeChecker {

    @Cache(persist = true)
    boolean isPalindrome(String line);
}
