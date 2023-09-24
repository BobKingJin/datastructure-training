package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-05-16 7:18
 */
public class Number1335 {

    public int minDifficulty(int[] jobDifficulty, int d) {

        if (jobDifficulty.length < d)
            return -1;

        // dp[i][j] 表示前 i 天完成前 j 项任务所需的最小难度，i、j都是从 0 开始数
        int[][] dp = new int[d][jobDifficulty.length];
        Arrays.stream(dp).forEach(i -> Arrays.fill(i, Integer.MAX_VALUE));
        int preDif = 0;

        for (int i = 0; i < jobDifficulty.length; ++i) {
            preDif = Math.max(preDif, jobDifficulty[i]);
            dp[0][i] = preDif;
        }

        for (int i = 1; i < d; i++) { // 前第 i 天
            for (int j = i; j < jobDifficulty.length; ++j) { // 前 j 项任务
                preDif = jobDifficulty[j];
                for (int k = j; k >= i; k--) { // 前 k 项任务
                    preDif = Math.max(preDif, jobDifficulty[k]);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k - 1] + preDif);
                }
            }
        }
        return dp[d - 1][jobDifficulty.length - 1];
    }
}
