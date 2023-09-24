package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-06-19 11:35
 */
public class Number1480 {

    public int[] runningSum(int[] nums) {

        int n = nums.length;
        int[] ans = new int[n];

        for (int i = 0, s = 0; i < n; i++)
            ans[i] = s = s + nums[i];

        return ans;
    }

}
