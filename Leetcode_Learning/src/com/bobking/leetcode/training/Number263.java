package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-06-04 20:14
 */
public class Number263 {

    public boolean isUgly(int n) {

        if (n <= 0)
            return false;

        while (n % 2 == 0)
            n /= 2;

        while (n % 3 == 0)
            n /= 3;

        while (n % 5 == 0)
            n /= 5;

        return n == 1;
    }
}
