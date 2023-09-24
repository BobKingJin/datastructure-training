package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-10-21 10:51
 */
public class Number1785 {

    public int minElements(int[] nums, int limit, int goal) {

        long sum = 0;

        for (int x : nums)
            sum += x;

        return (int)((Math.abs(sum - goal) + limit - 1) / limit);
    }
}
