package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-10 20:40
 */
public class Number1219 {

    int[][] g;
    boolean[][] vis;
    int m;
    int n;
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    // 参考：https://leetcode.cn/problems/path-with-maximum-gold/solution/gong-shui-san-xie-hui-su-suan-fa-yun-yon-scxo/
    public int getMaximumGold(int[][] grid) {
        g = grid;
        m = g.length;
        n = g[0].length;
        vis = new boolean[m][n];
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] != 0) {
                    vis[i][j] = true;
                    ans = Math.max(ans, dfs(i, j));
                    // 回溯
                    vis[i][j] = false;
                }
            }
        }
        return ans;
    }

    private int dfs(int x, int y) {
        int ans = g[x][y];
        for (int[] d : dirs) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                continue;
            if (g[nx][ny] == 0)
                continue;
            if (vis[nx][ny])
                continue;
            vis[nx][ny] = true;
            ans = Math.max(ans, g[x][y] + dfs(nx, ny));
            vis[nx][ny] = false;
        }
        return ans;
    }
}
