package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-07-23 14:52
 */
public class Number749 {

    private int[][] g;
    private int n;
    private int m;
    private int ans;
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    boolean[][] vis;

    // 参考：https://leetcode.cn/problems/contain-virus/solution/by-ac_oier-l9ya/
    public int containVirus(int[][] isInfected) {

        g = isInfected;
        n = g.length;
        m = g[0].length;
        while (true) {
            int cnt = getCnt();
            if (cnt == 0)
                break;
            ans += cnt;
        }

        return ans;
    }

    private int search(int _x, int _y, Set<Integer> s1, Set<Integer> s2) {

        int ans = 0;
        Deque<int[]> d = new ArrayDeque<int[]>();
        vis[_x][_y] = true;
        d.addLast(new int[]{_x, _y});
        s1.add(_x * m + _y);
        while (!d.isEmpty()) {
            int[] info = d.pollFirst();
            int x = info[0];
            int y = info[1];
            for (int[] di : dirs) {
                int nx = x + di[0];
                int ny = y + di[1];
                int loc = nx * m + ny;
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || vis[nx][ny])
                    continue;
                if (g[nx][ny] == 1) {
                    s1.add(loc);
                    vis[nx][ny] = true;
                    d.addLast(new int[]{nx, ny});
                } else if (g[nx][ny] == 0) {
                    s2.add(loc);
                    ans++;
                }
            }
        }

        return ans;
    }

    private int getCnt() {

        vis = new boolean[n][m];
        int max = 0;
        int ans = 0;
        // l1: 每个连通块的点集
        // s2: 每个连通块的候选感染点集
        List<Set<Integer>> l1 = new ArrayList<Set<Integer>>();
        List<Set<Integer>> l2 = new ArrayList<Set<Integer>>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (g[i][j] == 1 && !vis[i][j]) {
                    // s1: 当前连通块的点集
                    // s2: 当前连通块的候选感染点集
                    Set<Integer> s1 = new HashSet<Integer>();
                    Set<Integer> s2 = new HashSet<Integer>();
                    int b = search(i, j, s1, s2);
                    int a = s2.size();
                    if (a > max) {
                        max = a;
                        ans = b;
                    }
                    l1.add(s1);
                    l2.add(s2);
                }
            }
        }
        for (int i = 0; i < l2.size(); i++) {
            for (int loc : l2.get(i).size() == max ? l1.get(i) : l2.get(i)) {
                int x = loc / m, y = loc % m;
                g[x][y] = l2.get(i).size() == max ? -1 : 1;
            }
        }
        return ans;
    }
}
