package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-15 9:07
 */
public class Number2293 {

    public int minMaxGame(int[] nums) {

        int len = nums.length;

        while (len > 1) {
            for (int i = 0; i < len / 2; ++i) {
                if (i % 2 == 0) {
                    nums[i] = Math.min(nums[2 * i], nums[2 * i + 1]);
                } else {
                    nums[i] = Math.max(nums[2 * i], nums[2 * i + 1]);
                }
            }
            len /= 2;
        }
        return nums[0];
    }
}
