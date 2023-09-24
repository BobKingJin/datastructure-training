package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-03-21 10:02
 */
public class Number200 {

    // 参考：程序猿代码指南P342
    public int numIslands(char[][] grid) {

        if (grid == null || grid.length < 1)
            return 0;

        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    inject(grid, i, j);
                    res++;
                }
            }
        }

        return res;
    }

    private void inject(char[][] grid, int i, int j) {

        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1')
            return;

        grid[i][j] = '2';
        inject(grid, i + 1, j);
        inject(grid, i - 1, j);
        inject(grid, i, j + 1);
        inject(grid, i, j - 1);
    }
}
