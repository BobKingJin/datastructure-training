package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-06-23 9:43
 */
public class Number2596 {

    public boolean checkValidGrid(int[][] grid) {

        if (grid[0][0] != 0)
            return false;

        int n = grid.length;
        int[][] step = new int[n * n][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int id = grid[i][j];
                step[id][0] = i;
                step[id][1] = j;
            }
        }

        for (int i = 1; i < step.length; i++) {
            int x1 = Math.abs(step[i][0] - step[i - 1][0]);
            int y1 = Math.abs(step[i][1] - step[i - 1][1]);
            if ((x1 != 2 || y1 != 1) && (x1 != 1 || y1 != 2))
                return false;
        }
        return true;
    }
}
