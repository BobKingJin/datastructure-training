package com.bobking.leetcode.training;

/**
 * @Date: 2024/3/1 22:55
 * @Author: BobKing
 * @Description:
 */
public class Number2428 {

    public int maxSum(int[][] grid) {

        int ans = Integer.MIN_VALUE;

        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[0].length - 1; j++) {
                ans = Math.max(ans, grid[i - 1][j - 1] + grid[i - 1][j] + grid[i - 1][j + 1] + grid[i][j] + grid[i + 1][j - 1] + grid[i + 1][j] + grid[i + 1][j + 1]);
            }
        }
        return ans;
    }
}
