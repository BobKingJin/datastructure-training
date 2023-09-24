package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-18 23:25
 */
public class Number1941 {

    public boolean areOccurrencesEqual(String s) {

        int count[] = new int[26];
        for (char c : s.toCharArray())
            count[c - 'a']++;

        int f = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                if (f == -1) {
                    f = count[i];
                } else if (f != count[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
