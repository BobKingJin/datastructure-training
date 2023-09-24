package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-06-25 9:08
 */
public class Jianzhi_2_091 {

    // 参考：https://leetcode.cn/problems/JEj789/solution/tong-su-yi-dong-de-dpzuo-fa-su-kan-by-sm-2mt3/
    public int minCost(int[][] costs) {

        // 先定义状态：
        // dp[i][0] 代表第 i 间房子涂红色时 前 i + 1间房子累计所需的最小成本
        // dp[i][1] 代表第 i 间房子涂蓝色时 前 i + 1间房子累计所需的最小成本
        // dp[i][2] 代表第 i 间房子涂绿色时 前 i + 1间房子累计所需的最小成本
        // 确定状态转换：
        // dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
        // dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
        // dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];

        int[][] dp = new int[costs.length][3];
        // 第一件房子
        for (int i = 0; i < 3; i++)
            dp[0][i] = costs[0][i];

        for (int i = 1; i < costs.length; i++) {
            // 状态转换
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }

        return Math.min(dp[costs.length - 1][0],
                Math.min(dp[costs.length - 1][1], dp[costs.length - 1][2]));
    }
}
