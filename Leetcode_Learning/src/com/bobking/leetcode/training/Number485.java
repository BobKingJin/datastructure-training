package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-24 8:05
 */
public class Number485 {

    public int findMaxConsecutiveOnes(int[] nums) {

        int maxCount = 0;
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }

        maxCount = Math.max(maxCount, count);
        return maxCount;
    }
}
