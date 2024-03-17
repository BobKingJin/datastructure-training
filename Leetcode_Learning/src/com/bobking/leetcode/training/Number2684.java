package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2024/3/16 19:34
 * @Author: BobKing
 * @Description:
 */
public class Number2684 {

    private int ans;

    public int maxMoves1(int[][] grid) {
        for (int i = 0; i < grid.length; i++)
            dfs(i, 0, grid);
        return ans;
    }

    private void dfs(int i, int j, int[][] grid) {

        ans = Math.max(ans, j);

        if (ans == grid[0].length - 1)
            return;

        // 向右上/右/右下走一步
        for (int k = Math.max(i - 1, 0); k < Math.min(i + 2, grid.length); k++) {
            if (grid[k][j + 1] > grid[i][j])
                dfs(k, j + 1, grid);
        }
        grid[i][j] = 0;
    }

    public int maxMoves2(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[] vis = new int[m];
        Arrays.fill(vis, -1);

        List<Integer> q = new ArrayList<Integer>(m);

        for (int i = 0; i < m; i++)
            q.add(i);

        for (int j = 0; j < n - 1; j++) {
            List<Integer> nxt = new ArrayList<Integer>();
            for (int i : q) {
                for (int k = Math.max(i - 1, 0); k < Math.min(i + 2, m); k++) {
                    if (vis[k] != j && grid[k][j + 1] > grid[i][j]) {
                        // 第 k 行目前最右访问到了 j
                        vis[k] = j;
                        nxt.add(k);
                    }
                }
            }
            // 无法再往右走了
            if (nxt.isEmpty())
                return j;
            q = nxt;
        }
        return n - 1;
    }
}
