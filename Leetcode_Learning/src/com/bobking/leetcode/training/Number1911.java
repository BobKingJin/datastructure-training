package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-08 7:56
 */
public class Number1911 {

    public long maxAlternatingSum(int[] nums) {

        int n = nums.length;
        // 定义 f[i] 表示从前 i 个元素中选出的子序列，且最后一个元素为奇数下标时的最大交替和
        // 定义 g[i] 表示从前 i 个元素中选出的子序列，且最后一个元素为偶数下标时的最大交替和
        // 初始时 f[0] = g[0] = 0
        long[] f = new long[n + 1];
        long[] g = new long[n + 1];

        // 如果选取该元素且该元素为奇数下标，那么上一个元素必须为偶数下标，且只能从前 i − 1 个元素中选取，因此 f[i] = g[i - 1] - nums[i - 1]
        // 如果不选取该元素，那么 f[i] = f[i - 1]
        // 同理，如果选取该元素且该元素为偶数下标，那么上一个元素必须为奇数下标，且只能从前 i − 1 个元素中选取，因此 g[i] = f[i - 1] + nums[i - 1]
        // 如果不选取该元素，那么 g[i] = g[i - 1]
        // 综上，可以得到状态转移方程
        for (int i = 1; i <= n; ++i) {
            f[i] = Math.max(g[i - 1] - nums[i - 1], f[i - 1]);
            g[i] = Math.max(f[i - 1] + nums[i - 1], g[i - 1]);
        }

        return Math.max(f[n], g[n]);
    }
}
