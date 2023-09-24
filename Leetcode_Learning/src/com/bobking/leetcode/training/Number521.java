package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-03-05 10:28
 */
public class Number521 {

    public int findLUSlength(String a, String b) {

        if(a == null || a.length() == 0)
            return (b == null || b.length() == 0) ? -1 : b.length();

        if(a.equals(b))
            return -1;

        return a.length() >= b.length() ? a.length() : b.length();
    }
}
