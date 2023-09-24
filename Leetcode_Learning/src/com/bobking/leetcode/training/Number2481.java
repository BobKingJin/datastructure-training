package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-06-23 9:18
 */
public class Number2481 {

    public int numberOfCuts(int n) {

        if (n == 1 || n % 2 == 0)
            return n / 2;

        return n;
    }
}
