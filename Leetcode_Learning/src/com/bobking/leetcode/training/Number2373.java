package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-14 10:00
 */
public class Number2373 {

    public int[][] largestLocal(int[][] grid) {

        int n = grid.length;
        int size = 3;
        int[][] convolution = new int[n - size + 1][n - size + 1];

        for (int i = 0; i <= n - size; i++) {
            for (int j = 0; j <= n - size; j++)
                convolution[i][j] = findMaxVal(grid, i, j, size);
        }

        return convolution;
    }

    private int findMaxVal(int[][] grid, int i, int j, int size) {
        int maxVal = grid[i][j];
        for (int p = i; p < i + size; p++) {
            for (int q = j; q < j + size; q++) {
                if (grid[p][q] >= maxVal)
                    maxVal = grid[p][q];
            }
        }
        return maxVal;
    }
}
