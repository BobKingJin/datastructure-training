package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-30 0:26
 */
public class Number643 {

    // 参考：https://leetcode.cn/problems/maximum-average-subarray-i/solution/zi-shu-zu-zui-da-ping-jun-shu-i-by-leetc-us1k/
    public double findMaxAverage(int[] nums, int k) {

        int sum = 0;
        int n = nums.length;

        for (int i = 0; i < k; i++)
            sum += nums[i];

        int maxSum = sum;
        for (int i = k; i < n; i++) {
            sum = sum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, sum);
        }

        return 1.0 * maxSum / k;
    }
}
