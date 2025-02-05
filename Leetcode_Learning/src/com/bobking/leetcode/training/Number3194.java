package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @Date: 2025/2/5 23:28
 * @Author: BobKing
 * @Description:
 */
public class Number3194 {

    public double minimumAverage(int[] nums) {
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        int n = nums.length;
        for (int i = 0; i < n / 2; i++) {
            ans = Math.min(ans, nums[i] + nums[n - 1 - i]);
        }
        return ans / 2.0;
    }

}
