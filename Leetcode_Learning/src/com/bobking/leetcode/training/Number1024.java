package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-09-24 12:28
 */
public class Number1024 {

    // 参考：https://leetcode.cn/problems/video-stitching/solution/shi-pin-pin-jie-by-leetcode-solution/
    public int videoStitching1(int[][] clips, int time) {

        // 令 dp[i] 表示将区间 [0, i) 覆盖所需的最少子区间的数量
        // 由于希望子区间的数目尽可能少，因此可以将所有 dp[i] 的初始值设为一个大整数，并将 dp[0]（即空区间）的初始值设为 0
        // 可以枚举所有的子区间来依次计算出所有的 dp 值
        // 首先枚举 i，同时对于任意一个子区间 [aj, bj)，若其满足 aj < i ≤ bj，那么它就可以覆盖区间 [0, i) 的后半部分
        // 而前半部分则可以用 dp[aj] 对应的最优方法进行覆盖，因此可以用 dp[aj] + 1 来更新 dp[i]
        // 状态转移方程如下：dp[i] = min{dp[aj]} + 1(aj < i ≤ bj)

        int[] dp = new int[time + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;

        for (int i = 1; i <= time; i++) {
            for (int[] clip : clips) {
                if (clip[0] < i && i <= clip[1])
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
            }
        }

        return dp[time] == Integer.MAX_VALUE - 1 ? -1 : dp[time];
    }

    // 参考：https://leetcode.cn/problems/video-stitching/solution/shi-pin-pin-jie-by-leetcode-solution/
    public int videoStitching2(int[][] clips, int time) {

        int[] maxn = new int[time];
        int last = 0;
        int res = 0;
        int pre = 0;

        for (int[] clip : clips) {
            if (clip[0] < time)
                maxn[clip[0]] = Math.max(maxn[clip[0]], clip[1]);
        }

        for (int i = 0; i < time; i++) {

            last = Math.max(last, maxn[i]);

            if (i == last)
                return -1;

            if (i == pre) {
                res++;
                pre = last;
            }
        }

        return res;
    }
}
