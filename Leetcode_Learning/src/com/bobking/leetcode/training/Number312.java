package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-04-29 23:50
 */
public class Number312 {

    // 参考：程序猿代码指南P204
    public int maxCoins1(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] help = new int[nums.length + 2];
        help[0] = 1;
        help[nums.length + 1] = 1;
        for (int i = 0; i < nums.length; i++) {
            help[i + 1] = nums[i];
        }

        return process(help, 1, nums.length);
    }

    // L - R 范围内能获取的最大分数
    private int process(int[] help, int L, int R) {

        // 递归结束条件
        if (L == R) {
            return help[L - 1] * help[L] * help[R + 1];
        }

        int max = Math.max(
            process(help, L + 1, R) + help[L - 1] * help[L] * help[R + 1],
            process(help, L, R - 1) + help[L - 1] * help[R] * help[R + 1]);
        for (int i = L + 1; i < R; i++) {
            max = Math.max(max,
                process(help, L, i - 1) + process(help, i + 1, R) + help[L - 1] * help[i] * help[R
                    + 1]);
        }
        return max;
    }

    // 参考：程序猿代码指南P204
    public int maxCoins2(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 0) {
            return nums[0];
        }

        int N = nums.length;
        int[] help = new int[N + 2];
        help[0] = 1;
        help[N + 1] = 1;
        for (int i = 0; i < N; i++) {
            help[i + 1] = nums[i];
        }
        // dp[i][j] 表示范围 i - j 范围内能获取到的最大分数
        int[][] dp = new int[N + 2][N + 2];
        for (int i = 1; i <= N; i++) {
            dp[i][i] = help[i - 1] * help[i] * help[i + 1];
        }

        // 从下往上
        for (int L = N; L >= 1; L--) {
            // 从左往右
            for (int R = L + 1; R <= N; R++) {
                int finalL = dp[L + 1][R] + help[L - 1] * help[L] * help[R + 1];
                int finalR = dp[L][R - 1] + help[L - 1] * help[R] * help[R + 1];
                dp[L][R] = Math.max(finalL, finalR);
                for (int i = L + 1; i <= R - 1; i++) {
                    dp[L][R] = Math.max(dp[L][R],
                        dp[L][i - 1] + dp[i + 1][R] + help[L - 1] * help[i] * help[R + 1]);
                }
            }
        }

        return dp[1][N];
    }
}
