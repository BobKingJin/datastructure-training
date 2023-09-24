package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-17 23:04
 */
public class Number1827 {

    public int minOperations(int[] nums) {

        int res = 0;
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            max = nums[i] > max ? nums[i] : ++max;
            res += max - nums[i];
        }
        return res;
    }
}
