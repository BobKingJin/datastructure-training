package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-04 10:29
 */
public class Number1800 {

    public int maxAscendingSum(int[] nums) {

        int ans = 0;
        int sum = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (i == 0 || nums[i] > nums[i - 1]) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }

            ans = Math.max(ans, sum);
        }

        return ans;
    }
}
