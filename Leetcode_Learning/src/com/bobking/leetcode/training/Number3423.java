package com.bobking.leetcode.training;

/**
 * @Date: 2025/6/14 11:56
 * @Author: BobKing
 * @Description:
 */
public class Number3423 {

    public int maxAdjacentDistance(int[] nums) {
        int n = nums.length;
        int ans = Math.abs(nums[0] - nums[n - 1]);
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, Math.abs(nums[i] - nums[i - 1]));
        }
        return ans;
    }

}
