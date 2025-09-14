package com.bobking.leetcode.training;

/**
 * @Date: 2025/9/14 14:03
 * @Author: BobKing
 * @Description:
 */
public class Number2894 {

    public int differenceOfSums(int n, int m) {
        return n * (n + 1) / 2 - n / m * (n / m + 1) * m;
    }

}
