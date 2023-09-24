package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-11 11:05
 */
public class Number2283 {

    public boolean digitCount(String num) {

        int[] mp = new int[10];
        for (char c : num.toCharArray())
            mp[c - '0']++;

        for (int i = 0; i < num.length(); i++) {
            if (mp[i] != num.charAt(i) - '0')
                return false;
        }

        return true;
    }
}
