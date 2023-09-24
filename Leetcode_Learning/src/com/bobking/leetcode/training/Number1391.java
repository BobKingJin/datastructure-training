package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2023-04-26 21:32
 */
public class Number1391 {

    class DisjointSet {
        int[] f;

        public DisjointSet(int m, int n) {
            f = new int[m * n];
            for (int i = 0; i < m * n; ++i)
                f[i] = i;
        }

        public int find(int x) {
            return x == f[x] ? x : (f[x] = find(f[x]));
        }

        public void merge(int x, int y) {
            f[find(x)] = find(y);
        }
    }

    int[][] grid;
    int m;
    int n;
    DisjointSet ds;

    // 参考：https://leetcode.cn/problems/check-if-there-is-a-valid-path-in-a-grid/solution/jian-cha-wang-ge-zhong-shi-fou-cun-zai-you-xiao-lu/
    public boolean hasValidPath1(int[][] grid) {

        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        ds = new DisjointSet(m, n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j)
                handler(i, j);
        }
        return ds.find(getId(0, 0)) == ds.find(getId(m - 1, n - 1));
    }

    public int getId(int x, int y) {
        return x * n + y;
    }

    public void detectL(int x, int y) {
        if (y - 1 >= 0 && (grid[x][y - 1] == 4 || grid[x][y - 1] == 6 || grid[x][y - 1] == 1))
            ds.merge(getId(x, y), getId(x, y - 1));
    }

    public void detectR(int x, int y) {
        if (y + 1 < n && (grid[x][y + 1] == 3 || grid[x][y + 1] == 5 || grid[x][y + 1] == 1))
            ds.merge(getId(x, y), getId(x, y + 1));
    }

    public void detectU(int x, int y) {
        if (x - 1 >= 0 && (grid[x - 1][y] == 3 || grid[x - 1][y] == 4 || grid[x - 1][y] == 2))
            ds.merge(getId(x, y), getId(x - 1, y));
    }

    public void detectD(int x, int y) {
        if (x + 1 < m && (grid[x + 1][y] == 5 || grid[x + 1][y] == 6 || grid[x + 1][y] == 2))
            ds.merge(getId(x, y), getId(x + 1, y));
    }

    public void handler(int x, int y) {
        switch (grid[x][y]) {
            case 1:
                detectL(x, y);
                detectR(x, y);
                break;
            case 2:
                detectU(x, y);
                detectD(x, y);
                break;
            case 3:
                detectL(x, y);
                detectD(x, y);
                break;
            case 4:
                detectR(x, y);
                detectD(x, y);
                break;
            case 5:
                detectL(x, y);
                detectU(x, y);
                break;
            case 6:
                detectR(x, y);
                detectU(x, y);
        }
    }

    public boolean hasValidPath2(int[][] grid) {

        // 0: up, 1: down, 2: left, 3: right,
        int[][] direction = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] conn = new int[][]{{2, 3}, {0, 1}, {1, 2}, {1, 3}, {0, 2}, {0, 3}};
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] dp = new boolean[m][n];
        dp[m - 1][n - 1] = true;

        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{m - 1, n - 1});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int neighbor : conn[grid[cur[0]][cur[1]] - 1]) {
                int x = cur[0] + direction[neighbor][0];
                int y = cur[1] + direction[neighbor][1];
                // 判断需要邻居坐标处是否有道路到达当前位置
                int target = neighbor < 2 ? 1 - neighbor : 5 - neighbor;
                if (x >= 0 && x < m && y >= 0 && y < n && !dp[x][y]
                        && (conn[grid[x][y] - 1][0] == target || conn[grid[x][y] - 1][1] == target)) {
                    dp[x][y] = true;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return dp[0][0];
    }
}
