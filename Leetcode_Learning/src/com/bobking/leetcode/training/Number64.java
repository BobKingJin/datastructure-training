package com.bobking.leetcode.training;

public class Number64 {

    // 参考：程序猿代码指南P185
    public int minPathSum1(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)
            return 0;
        // dp[i][j] 表示从 (0, 0) 走到 (i, j) 的最小路径和
        int[][] dp = new int[grid.length][grid[0].length];

        dp[0][0] = grid[0][0];
        // 第一列
        for (int i = 1; i < dp.length; i++)
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        // 第一行
        for (int i = 1; i < dp[0].length; i++)
            dp[0][i] = dp[0][i - 1] + grid[0][i];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++)
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }

    // 参考：程序猿代码指南P185
    public int minPathSum2(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)
            return 0;

        int more = Math.max(grid.length, grid[0].length);
        int less = Math.min(grid.length, grid[0].length);
        boolean rowMore = more == grid.length;

        int[] dp = new int[less];
        dp[0] = grid[0][0];
        for (int i = 1; i < less; i++)
            dp[i] = dp[i - 1] + (rowMore ? grid[0][i] : grid[i][0]);

        for (int i = 1; i < more; i++) {
            dp[0] = dp[0] + (rowMore ? grid[i][0] : grid[0][i]);
            for (int j = 1; j < less; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + (rowMore ? grid[i][j] : grid[j][i]);
            }
        }

        return dp[less - 1];
    }
}
