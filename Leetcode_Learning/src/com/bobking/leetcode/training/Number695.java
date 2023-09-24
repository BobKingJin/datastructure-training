package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-05-15 15:27
 */
public class Number695 {

    // 参考：https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
    public int maxAreaOfIsland(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                    res = Math.max(res, maxArea(grid, i, j));
            }
        }

        return res;
    }

    private int maxArea(int[][] grid, int i, int j) {

        if (!inIsland(grid, i, j))
            return 0;

        if (grid[i][j] != 1)
            return 0;

        grid[i][j] = 2;
        return 1 + maxArea(grid, i + 1, j)
                + maxArea(grid, i - 1, j)
                + maxArea(grid, i, j + 1)
                + maxArea(grid, i, j - 1);
    }

    private boolean inIsland(int[][] grid, int i, int j) {

        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length)
            return true;
        return false;
    }

}
