package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-02-24 21:37
 */
public class Number2357 {

    public int minimumOperations(int[] nums) {

        int ans = 0;
        Arrays.sort(nums);
        int length = nums.length;

        for (int i = 0; i < length; i++) {
            if (nums[i] > 0) {
                subtract(nums, nums[i], i);
                ans++;
            }
        }
        return ans;
    }

    private void subtract(int[] nums, int x, int startIndex) {
        int length = nums.length;
        for (int i = startIndex; i < length; i++)
            nums[i] -= x;
    }
}
