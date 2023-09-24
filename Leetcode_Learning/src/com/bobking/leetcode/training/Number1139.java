package com.bobking.leetcode.training;

public class Number1139 {

    private void setSortedMap(int[][] right, int[][] down, int[][] grid) {

        int row = grid.length;
        int column = grid[0].length;

        right[row - 1][column - 1] = grid[row - 1][column - 1] == 1 ? 1 : 0;
        down[row - 1][column - 1] = grid[row - 1][column - 1] == 1 ? 1 : 0;
        // 处理最后一列
        for (int i = row - 2; i >= 0; i--) {
            down[i][column - 1] = grid[i][column - 1] == 1 ? 1 + down[i + 1][column - 1] : 0;
            right[i][column - 1] = grid[i][column - 1] == 1 ? 1 : 0;
        }
        // 处理最后一行
        for (int i = column - 2; i >= 0; i--) {
            right[row - 1][i] = grid[row - 1][i] == 1 ? 1 + right[row - 1][i + 1] : 0;
            down[row - 1][i] = grid[row - 1][i] == 1 ? 1 : 0;
        }
        for (int i = row - 2; i >= 0; i--) {
            for (int j = column - 2; j >= 0; j--) {
                down[i][j] = grid[i][j] == 1 ? 1 + down[i + 1][j] : 0;
                right[i][j] = grid[i][j] == 1 ? 1 + right[i][j + 1] : 0;
            }
        }
    }

    // 参考：程序猿代码指南P407
    public int largest1BorderedSquare(int[][] grid) {

        if (grid == null || grid.length == 0)
            return 0;

        int[][] right = new int[grid.length][grid[0].length];
        int[][] down = new int[grid.length][grid[0].length];

        setSortedMap(right, down, grid);
        for (int size = Math.min(grid.length, grid[0].length); size != 0; size--) {
            if (hasSizeOfBorder(size, right, down))
                return size * size;
        }

        return 0;
    }

    private boolean hasSizeOfBorder(int size, int[][] right, int[][] down) {

        for (int i = 0; i < right.length - size + 1; i++) {
            for (int j = 0; j < right[0].length - size + 1; j++) {
                if (right[i][j] >= size && down[i][j] >= size
                        && right[i + size - 1][j] >= size
                        && down[i][j + size - 1] >= size) {
                    return true;
                }
            }
        }

        return false;
    }

}
