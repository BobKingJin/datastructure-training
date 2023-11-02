package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-30 6:11
 */
public class Number2735 {

    // 参考: https://leetcode.cn/problems/collecting-chocolates/solutions/2304896/qiao-miao-mei-ju-pythonjavacgo-by-endles-5ws2/
    public long minCost(int[] nums, int x) {

        int n = nums.length;

        long[] sum = new long[n];

        // 操作 i 次
        for (int i = 0; i < n; i++)
            sum[i] = (long) i * x;

        // 子数组左端点
        for (int i = 0; i < n; i++) {
            int mn = nums[i];
            // 子数组右端点(把数组视作环形的)
            for (int j = i; j < n + i; j++) {
                // 从 nums[i] 到 nums[j % n] 的最小值
                mn = Math.min(mn, nums[j % n]);
                // 累加操作 j - i 次的成本
                sum[j - i] += mn;
            }
        }

        long ans = Long.MAX_VALUE;

        for (long s : sum)
            ans = Math.min(ans, s);

        return ans;
    }
}
