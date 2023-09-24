package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Number417 {

    int n;
    int m;
    int[][] g;
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    // 参考：https://leetcode.cn/problems/pacific-atlantic-water-flow/solution/by-ac_oier-do7d/
    public List<List<Integer>> pacificAtlantic1(int[][] heights) {

        g = heights;
        m = g.length;
        n = g[0].length;
        Deque<int[]> d1 = new ArrayDeque<int[]>();
        Deque<int[]> d2 = new ArrayDeque<int[]>();
        boolean[][] res1 = new boolean[m][n];
        boolean[][] res2 = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    res1[i][j] = true;
                    d1.addLast(new int[]{i, j});
                }
                if (i == m - 1 || j == n - 1) {
                    res2[i][j] = true;
                    d2.addLast(new int[]{i, j});
                }
            }
        }

        bfs(d1, res1);
        bfs(d2, res2);

        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (res1[i][j] && res2[i][j]) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
            }
        }

        return ans;
    }

    private void bfs(Deque<int[]> d, boolean[][] res) {

        while (!d.isEmpty()) {
            int[] info = d.pollFirst();
            int x = info[0];
            int y = info[1];
            int t = g[x][y];
            for (int[] di : dirs) {
                int nx = x + di[0];
                int ny = y + di[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                    continue;
                if (res[nx][ny] || g[nx][ny] < t)
                    continue;
                d.addLast(new int[]{nx, ny});
                res[nx][ny] = true;
            }
        }
    }

    // 参考：https://leetcode.cn/problems/pacific-atlantic-water-flow/solution/by-ac_oier-do7d/
    public List<List<Integer>> pacificAtlantic2(int[][] heights) {

        g = heights;
        m = g.length;
        n = g[0].length;
        boolean[][] res1 = new boolean[m][n];
        boolean[][] res2 = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    if (!res1[i][j])
                        dfs(i, j, res1);
                }
                if (i == m - 1 || j == n - 1) {
                    if (!res2[i][j])
                        dfs(i, j, res2);
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (res1[i][j] && res2[i][j]) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
            }
        }

        return ans;
    }

    private void dfs(int x, int y, boolean[][] res) {

        res[x][y] = true;

        for (int[] di : dirs) {
            int nx = x + di[0];
            int ny = y + di[1];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                continue;
            if (res[nx][ny] || g[nx][ny] < g[x][y])
                continue;
            dfs(nx, ny, res);
        }
    }

    int N = 200 * 200 + 10;
    int[] p1 = new int[N];
    int[] p2 = new int[N];
    int tot;
    int S;
    int T;

    private void union(int[] p, int a, int b) {
        p[find(p, a)] = p[find(p, b)];
    }

    private int find(int[] p, int x) {
        if (p[x] != x)
            p[x] = find(p, p[x]);
        return p[x];
    }

    private boolean query(int[] p, int a, int b) {
        return find(p, a) == find(p, b);
    }

    private int getIdx(int x, int y) {
        return x * n + y;
    }

    // 参考：https://leetcode.cn/problems/pacific-atlantic-water-flow/solution/by-ac_oier-do7d/
    public List<List<Integer>> pacificAtlantic3(int[][] heights) {
        g = heights;
        m = g.length;
        n = g[0].length;
        tot = m * n;
        S = tot + 1;
        T = tot + 2;

        for (int i = 0; i <= T; i++)
            p1[i] = p2[i] = i;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int idx = getIdx(i, j);
                if (i == 0 || j == 0) {
                    if (!query(p1, S, idx))
                        dfs(p1, S, i, j);
                }
                if (i == m - 1 || j == n - 1) {
                    if (!query(p2, T, idx))
                        dfs(p2, T, i, j);
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int idx = getIdx(i, j);
                if (query(p1, S, idx) && query(p2, T, idx)) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    private void dfs(int[] p, int ori, int x, int y) {
        union(p, ori, getIdx(x, y));
        for (int[] di : dirs) {
            int nx = x + di[0];
            int ny = y + di[1];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                continue;
            if (query(p, ori, getIdx(nx, ny)) || g[nx][ny] < g[x][y])
                continue;
            dfs(p, ori, nx, ny);
        }
    }

}
