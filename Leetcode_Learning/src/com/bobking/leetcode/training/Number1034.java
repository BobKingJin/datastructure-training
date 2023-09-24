package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author BobKing
 * @create 2022-09-10 10:21
 */
public class Number1034 {

    // 参考：https://leetcode.cn/problems/coloring-a-border/solution/gong-shui-san-xie-tu-lun-sou-suo-zhuan-t-snvw/
    public int[][] colorBorder1(int[][] grid, int row, int col, int color) {

        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m][n];
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Deque<int[]> d = new ArrayDeque<int[]>();
        d.addLast(new int[]{row, col});

        while (!d.isEmpty()) {

            int[] poll = d.pollFirst();
            int x = poll[0];
            int y = poll[1];
            int cnt = 0;

            for (int[] di : dirs) {

                int nx = x + di[0];
                int ny = y + di[1];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                    continue;

                if (grid[x][y] != grid[nx][ny]) {
                    continue;
                } else {
                    cnt++;
                }

                if (ans[nx][ny] != 0)
                    continue;

                d.addLast(new int[]{nx, ny});
            }

            ans[x][y] = cnt == 4 ? grid[x][y] : color;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (ans[i][j] == 0)
                    ans[i][j] = grid[i][j];
            }

        }

        return ans;
    }

    int m;
    int n;
    int c;
    int[][] grids;
    int[][] ans;
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    // 参考：https://leetcode.cn/problems/coloring-a-border/solution/gong-shui-san-xie-tu-lun-sou-suo-zhuan-t-snvw/
    public int[][] colorBorder2(int[][] grid, int row, int col, int color) {

        grids = grid;
        c = color;
        m = grid.length;
        n = grid[0].length;
        ans = new int[m][n];

        dfs(row, col);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (ans[i][j] == 0)
                    ans[i][j] = grids[i][j];
            }
        }
        return ans;
    }

    private void dfs(int x, int y) {

        int cnt = 0;

        for (int[] di : dirs) {

            int nx = x + di[0];
            int ny = y + di[1];

            if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                continue;

            if (grids[x][y] != grids[nx][ny]) {
                continue;
            } else {
                cnt++;
            }

            if (ans[nx][ny] != 0)
                continue;

            ans[nx][ny] = -1;
            dfs(nx, ny);
        }

        ans[x][y] = cnt == 4 ? grids[x][y] : c;
    }
}
