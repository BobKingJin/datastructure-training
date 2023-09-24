package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-12 22:48
 */
public class Number961 {

    int[] cnts = new int[10010];

    public int repeatedNTimes(int[] nums) {
        for (int x : nums) {
            if (++cnts[x] > 1)
                return x;
        }
        return -1;
    }
}
