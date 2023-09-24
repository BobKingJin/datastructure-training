package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2022-05-03 19:49
 */
public class Number329 {

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int rows;
    int columns;

    // 参考：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/solution/ju-zhen-zhong-de-zui-chang-di-zeng-lu-jing-by-le-2/
    public int longestIncreasingPath1(int[][] matrix) {
        
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        
        rows = matrix.length;
        columns = matrix[0].length;
        int[][] memo = new int[rows][columns];
        
        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                res = Math.max(res, dfs(matrix, i, j, memo));
        }

        return res;
    }

    private int dfs(int[][] matrix, int row, int column, int[][] memo) {

        if (memo[row][column] != 0)
            return memo[row][column];

        // 注意
        memo[row][column]++;
        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newColumn = column + dir[1];
            if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && matrix[newRow][newColumn] > matrix[row][column])
                memo[row][column] = Math.max(memo[row][column], dfs(matrix, newRow, newColumn, memo) + 1);
        }

        return memo[row][column];
    }

    // 参考：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/solution/ju-zhen-zhong-de-zui-chang-di-zeng-lu-jing-by-le-2/
    public int longestIncreasingPath2(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        rows = matrix.length;
        columns = matrix[0].length;
        int[][] outdegrees = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                for (int[] dir : dirs) {
                    int newRow = i + dir[0];
                    int newColumn = j + dir[1];
                    if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && matrix[newRow][newColumn] > matrix[i][j])
                        outdegrees[i][j]++;
                }
            }
        }

        Queue<int[]> queue = new LinkedList<int[]>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (outdegrees[i][j] == 0)
                    queue.offer(new int[]{i, j});
            }
        }

        int res = 0;
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] cell = queue.poll();
                int row = cell[0];
                int column = cell[1];
                for (int[] dir : dirs) {
                    int newRow = row + dir[0];
                    int newColumn = column + dir[1];
                    if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && matrix[newRow][newColumn] < matrix[row][column]) {
                        outdegrees[newRow][newColumn]--;
                        if (outdegrees[newRow][newColumn] == 0)
                            queue.offer(new int[]{newRow, newColumn});
                    }
                }
            }
        }

        return res;
    }
}
