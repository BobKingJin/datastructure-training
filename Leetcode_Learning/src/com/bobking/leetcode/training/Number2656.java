package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-11-15 8:11
 */
public class Number2656 {

    public int maximizeSum(int[] nums, int k) {

        int m = 0;

        for (int x : nums)
            m = Math.max(m, x);

        return (m * 2 + k - 1) * k / 2;
    }
}
