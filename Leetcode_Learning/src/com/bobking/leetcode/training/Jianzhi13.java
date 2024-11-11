package com.bobking.leetcode.training;

import java.util.Stack;

/**
 * @Date: 2024/11/11 10:22
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi13 {

    public int movingCount1(int threshold, int rows, int cols) {
        int flag[][] = new int[rows][cols];
        return dfs(0, 0, rows, cols, flag, threshold);
    }

    private int dfs(int i, int j, int rows, int cols, int[][] flag, int threshold) {
        if (i < 0 || i >= rows || j < 0 || j >= cols || sum(i) + sum(j) > threshold || flag[i][j] == 1)
            return 0;
        flag[i][j] = 1;
        return dfs(i - 1, j, rows, cols, flag, threshold)
                + dfs(i + 1, j, rows, cols, flag, threshold)
                + dfs(i, j - 1, rows, cols, flag, threshold)
                + dfs(i, j + 1, rows, cols, flag, threshold)
                + 1;
    }

    private int sum(int i) {
        int sum = 0;
        do {
            sum += i % 10;
        } while ((i = i / 10) > 0);
        return sum;
    }

    public int movingCount2(int threshold, int rows, int cols) {
        if (rows <= 0 || cols <= 0 || threshold < 0)
            return 0;
        Stack<Integer> s = new Stack<Integer>();
        boolean[] visited = new boolean[rows * cols];
        int[][] xoy = {{0, 1, 0, -1}, {1, 0, -1, 0}};
        int count = 0;
        s.add(0);
        visited[0] = true;
        while (!s.empty()) {
            int cur = s.pop();
            count++;
            for (int i = 0; i < 4; i++) {
                int x = cur % cols + xoy[0][i];
                int y = cur / cols + xoy[1][i];
                int sum = getDigitSum(x) + getDigitSum(y);
                if (x >= 0 && x < cols && y >= 0 && y < rows
                        && sum <= threshold && !visited[x + y * cols]) {
                    s.add(x + y * cols);
                    visited[x + y * cols] = true;
                }
            }
        }
        return count;
    }

    private int getDigitSum(int i) {
        int sum = 0;
        while (i > 0) {
            sum += i % 10;
            i /= 10;
        }
        return sum;
    }
}
