package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-19 8:00
 */
public class Number2574 {

    public int[] leftRightDifference(int[] nums) {

        int n = nums.length;
        int rightSum = 0;

        for (int num : nums)
            rightSum += num;

        int[] ans = new int[n];

        int leftSum = 0;
        for (int i = 0; i < n; i++) {
            rightSum -= nums[i];
            ans[i] = Math.abs(rightSum - leftSum);
            leftSum += nums[i];
        }

        return ans;
    }
}
