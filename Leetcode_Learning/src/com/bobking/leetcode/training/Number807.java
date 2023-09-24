package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-08-06 22:09
 */
public class Number807 {

    public int maxIncreaseKeepingSkyline(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        int[] r = new int[n];
        int[] c = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                r[i] = Math.max(r[i], grid[i][j]);
                c[j] = Math.max(c[j], grid[i][j]);
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                ans += Math.min(r[i], c[j]) - grid[i][j];
        }

        return ans;
    }
}
