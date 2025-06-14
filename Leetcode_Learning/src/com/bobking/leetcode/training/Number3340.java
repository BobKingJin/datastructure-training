package com.bobking.leetcode.training;

/**
 * @Date: 2025/6/14 12:01
 * @Author: BobKing
 * @Description:
 */
public class Number3340 {

    public boolean isBalanced(String num) {
        int s = 0;
        char[] digits = num.toCharArray();
        for (int i = 0; i < digits.length; i++) {
            int c = digits[i] - '0';
            s += i % 2 > 0 ? c : -c;
        }
        return s == 0;
    }

}
