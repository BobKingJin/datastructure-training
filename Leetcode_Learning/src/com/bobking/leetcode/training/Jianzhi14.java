package com.bobking.leetcode.training;

/**
 * @Date: 2026/6/29 0:28
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi14 {

    public int cutRope(int n) {

        if (n <= 3) {
            return n - 1;
        }
        // dp[i]表示长度为 i 的绳子可以被剪出来的最大乘积
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 4;

        for (int i = 5; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], j * dp[i - j]);
            }
        }
        return dp[n];
    }

}
