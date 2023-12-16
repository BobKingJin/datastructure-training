package com.bobking.leetcode.training;

/**
 * @Date: 2023/12/13 7:48
 * @Author: BobKing
 * @Description:
 */
public class Number2697 {

    public String makeSmallestPalindrome(String s) {

        char[] ch = s.toCharArray();

        for (int i = 0, n = ch.length; i < n / 2; i++) {

            char x = ch[i];
            char y = ch[n - 1 - i];

            if (x > y) {
                ch[i] = y;
            } else {
                ch[n - 1 - i] = x;
            }
        }

        return new String(ch);
    }
}
