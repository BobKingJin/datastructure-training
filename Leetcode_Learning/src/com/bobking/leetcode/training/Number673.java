package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-09 15:54
 */
public class Number673 {

    // 参考：https://leetcode.cn/problems/number-of-longest-increasing-subsequence/solution/gong-shui-san-xie-lis-de-fang-an-shu-wen-obuz/
    public int findNumberOfLIS(int[] nums) {

        int n = nums.length;
        int[] f = new int[n];
        // 定义 g[i] 为考虑以 nums[i] 结尾的最长上升子序列的个数
        int[] g = new int[n];
        int max = 1;

        // 结合 f[i] 的转移过程，不失一般性地考虑 g[i] 该如何转移：
        // 由于每个数都能独自一个成为子序列，因此起始必然有 g[i] = 1
        // 枚举区间 [0, i) 的所有数 nums[j]，如果满足 nums[j] < nums[i]，说明 nums[i] 可以接在 nums[j] 后面形成上升子序列
        // 这时候对 f[i] 和 f[j] + 1 的大小关系进行分情况讨论：
        // 满足 f[i] < f[j] + 1：说明 f[i] 会被 f[j] + 1 直接更新，此时同步直接更新 g[i] = g[j] 即可
        // 满足 f[i] = f[j] + 1：说明找到了一个新的符合条件的前驱，此时将值继续累加到方案数当中，即有 g[i] += g[j]

        for (int i = 0; i < n; i++) {
            f[i] = 1;
            g[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (f[i] < f[j] + 1) {
                        f[i] = f[j] + 1;
                        g[i] = g[j];
                    } else if (f[i] == f[j] + 1) {
                        g[i] += g[j];
                    }
                }
            }
            max = Math.max(max, f[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (f[i] == max)
                ans += g[i];
        }

        return ans;
    }
}
