package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-06-19 11:15
 */
public class Number2304 {

    public int minPathCost(int[][] grid, int[][] moveCost) {

        // 定义 dp[i][j] 表示从第一行出发到达第 i 行第 j 列时的最小路径代价
        // 枚举从第 i - 1 行的第 k 列转移过来，取最小值
        // dp[i][j] = grid[i][j] + dp[i − 1][k](0 <= k <= n - 1) + moveCost[grid[i − 1][k]][j]
        // 可以用滚动数组优化

        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < dp.length; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);

        for (int j = 0; j < m; j++)
            dp[0][j] = grid[0][j];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < m; k++)
                    dp[i][j] = Math.min(dp[i - 1][k] + moveCost[grid[i - 1][k]][j] + grid[i][j], dp[i][j]);
            }
        }

        for (int j = 0; j < m; j++)
            ans = Math.min(ans, dp[n - 1][j]);

        return ans;
    }
}
