package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Interview08_02 {

    List<List<Integer>> res = new LinkedList<List<Integer>>();

    // 参考：https://leetcode-cn.com/problems/robot-in-a-grid-lcci/solution/dong-tai-gui-hua-by-wushaoling/
    public List<List<Integer>> pathWithObstacles1(int[][] obstacleGrid) {

        if (obstacleGrid == null)
            return res;

        int row = obstacleGrid.length;
        if (row == 0)
            return res;

        int col = obstacleGrid[0].length;
        if (col == 0)
            return res;

        // 起点或终点是障碍，不可能到达
        if (obstacleGrid[0][0] == 1 || obstacleGrid[row - 1][col - 1] == 1)
            return res;

        // dp[i][j] 表示从 (0, 0) - (i, j) 有多少种可能
        int[][] dp = new int[row][col];
        dp[0][0] = 1;
        // 初始化首列
        for (int i = 1; i < row; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
                // 后面不用再尝试，直接置为不可达
                break;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        // 初始化首行
        for (int i = 1; i < col; i++) {
            if (obstacleGrid[0][i] == 1) {
                dp[0][i] = 0;
                // 后面不用再尝试，直接置为不可达
                break;
            } else {
                dp[0][i] = dp[0][i - 1];
            }
        }
        // 求路径
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    // 无需保存和，保存最大的即可
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 到达终点的路径为 0，直接返回
        if (dp[row - 1][col - 1] == 0)
            return res;

        // 从终点反推回起点
        int r = row - 1;
        int c = col - 1;
        while (r != 0 || c != 0) {

            res.add(Arrays.asList(r, c));

            int up = 0;
            if (r > 0)
                up = dp[r - 1][c];

            int left = 0;
            if (c > 0)
                left = dp[r][c - 1];

            if (up >= left) {
                r--;
            } else {
                c--;
            }
        }

        res.add(Arrays.asList(0, 0));
        Collections.reverse(res);
        return res;
    }

    // 记录路径
    List<List<Integer>> path = new LinkedList<List<Integer>>();
    // 行数
    int r = 0;
    // 列数
    int c = 0;

    // 参考：https://leetcode-cn.com/problems/robot-in-a-grid-lcci/solution/mi-lu-de-ji-qi-ren-zhu-shi-xiang-xi-po-su-si-lu-by/
    public List<List<Integer>> pathWithObstacles2(int[][] obstacleGrid) {

        if (obstacleGrid == null)
            return path;

        r = obstacleGrid.length;
        if (r == 0)
            return path;

        c = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[r - 1][c - 1] == 1)
            return path;

        // 记录数组
        boolean[][] visit = new boolean[r][c];
        backtrack(obstacleGrid, 0, 0, visit);
        return path;
    }

    // 是否可以从 (i, j) 走到 (r - 1, c - 1)
    private boolean backtrack(int[][] obstacleGrid, int x, int y, boolean[][] visit) {

        // 越界，有障碍，已访问
        if (x >= r || y >= c || obstacleGrid[x][y] == 1 || visit[x][y])
            return false;

        // 如果不是以上情况，说明这个格子值得探索，做出选择
        path.add(Arrays.asList(x, y));
        // 1  2  3  4      如果由 2 到 6 如果 6 返回 false
        // 5  6  7  8      那么由 5 到 6 则直接返回 false
        // 9  10 11 12     即 visit[x][y] 是不用回溯，从 (i, j) 走到 (r - 1, c - 1) 到底是否为 true
        // 13 14 15 16     跟从哪个位置出发没关系，是不用回溯的
        visit[x][y] = true;
        // 选择后是否到达终点
        if (x == r - 1 && y == c - 1)
            return true;

        // 选择后没到终点，先尝试向下，再尝试向右
        if (backtrack(obstacleGrid, x + 1, y, visit) || backtrack(obstacleGrid, x, y + 1, visit))
            return true;

        // 回溯
        // 既不能向下也不能向右，撤销选择
        path.remove(path.size() - 1);
        return false;
    }

}
