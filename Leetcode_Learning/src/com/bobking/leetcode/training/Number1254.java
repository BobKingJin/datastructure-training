package com.bobking.leetcode.training;

public class Number1254 {

    public int closedIsland(int[][] grid) {

        if (grid == null || grid[0] == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int res = 0;
        // 注意边界直接跳过了，即第一行和第一列
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (grid[i][j] == 0) {
                    if (dfs(grid, i, j))
                        res++;
                }
            }
        }
        return res;
    }

    private boolean dfs(int[][] grid, int i, int j) {

        int rows = grid.length;
        int cols = grid[0].length;
        if (i < 0 || j < 0 || i >= rows || j >= cols)
            return false;

        if (grid[i][j] == 1)
            return true;

        grid[i][j] = 1;
        boolean up = dfs(grid, i - 1, j);
        boolean down = dfs(grid, i + 1, j);
        boolean left = dfs(grid, i, j - 1);
        boolean right = dfs(grid, i, j + 1);
        if (up && down && left && right)
            return true;

        return false;
    }
}
