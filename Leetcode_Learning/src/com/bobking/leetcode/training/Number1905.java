package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-25 9:47
 */
public class Number1905 {

    int r;
    int c;

    // 参考：https://leetcode.cn/problems/count-sub-islands/solution/1905tong-ji-zi-dao-yu-ji-he-zhong-die-fa-dahq/
    public int countSubIslands(int[][] grid1, int[][] grid2) {

        r = grid1.length;
        c = grid1[0].length;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                // 将两个图叠放在一起
                if (grid2[i][j] == 1)
                    grid2[i][j] += grid1[i][j];
            }
        }

        int res = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                // DFS BFS任选一个
                if (grid2[i][j] == 2 && fill(grid2, i, j))
                    res++;
            }
        }

        return res;
    }

    private boolean fill(int[][] grid, int i, int j) {

        if (i < 0 || i >= r || j < 0 || j >= c)
            return true;

        if (grid[i][j] != 2)
            return grid[i][j] == 0;

        grid[i][j] = 0;
        boolean down = fill(grid, i - 1, j);
        boolean up = fill(grid, i + 1, j);
        boolean right = fill(grid, i, j - 1);
        boolean left = fill(grid, i, j + 1);
        return down & up & right & left;
    }

}
