package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-26 7:55
 */
public class Number520 {

    public boolean detectCapitalUse(String word) {

        if (word.toUpperCase().equals(word))
            return true;
        if (word.toLowerCase().equals(word))
            return true;

        int n = word.length();
        int idx = 1;

        if (Character.isUpperCase(word.charAt(0))) {
            while (idx < n && Character.isLowerCase(word.charAt(idx)))
                idx++;
        }
        return idx == n;
    }
}
