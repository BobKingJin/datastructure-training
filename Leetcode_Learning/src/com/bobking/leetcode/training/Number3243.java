package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2025/3/8 15:59
 * @Author: BobKing
 * @Description:
 */
public class Number3243 {

    // 参考: https://leetcode.cn/problems/shortest-distance-after-road-addition-queries-i/solutions/2869215/liang-chong-fang-fa-bfs-dppythonjavacgo-mgunf/?envType=daily-question&envId=2025-03-08
    public int[] shortestDistanceAfterQueries1(int n, int[][] queries) {
        List<Integer>[] g = new ArrayList[n - 1];
        Arrays.setAll(g, i -> new ArrayList<Integer>());
        for (int i = 0; i < n - 1; i++) {
            g[i].add(i + 1);
        }

        int[] ans = new int[queries.length];
        int[] vis = new int[n - 1];
        for (int i = 0; i < queries.length; i++) {
            g[queries[i][0]].add(queries[i][1]);
            ans[i] = bfs(i + 1, g, vis, n);
        }
        return ans;
    }

    private int bfs(int i, List<Integer>[] g, int[] vis, int n) {
        List<Integer> q = new ArrayList<Integer>();
        q.add(0);
        for (int step = 1; ; step++) {
            List<Integer> tmp = q;
            q = new ArrayList<Integer>();
            for (int x : tmp) {
                for (int y : g[x]) {
                    if (y == n - 1) {
                        return step;
                    }
                    if (vis[y] != i) {
                        vis[y] = i;
                        q.add(y);
                    }
                }
            }
        }
    }

    // 参考: https://leetcode.cn/problems/shortest-distance-after-road-addition-queries-i/solutions/2869215/liang-chong-fang-fa-bfs-dppythonjavacgo-mgunf/?envType=daily-question&envId=2025-03-08
    public int[] shortestDistanceAfterQueries2(int n, int[][] queries) {
        List<Integer>[] from = new ArrayList[n];
        Arrays.setAll(from, i -> new ArrayList<Integer>());
        int[] f = new int[n];
        for (int i = 1; i < n; i++) {
            f[i] = i;
        }

        int[] ans = new int[queries.length];
        for (int qi = 0; qi < queries.length; qi++) {
            int l = queries[qi][0];
            int r = queries[qi][1];
            from[r].add(l);
            if (f[l] + 1 < f[r]) {
                f[r] = f[l] + 1;
                for (int i = r + 1; i < n; i++) {
                    f[i] = Math.min(f[i], f[i - 1] + 1);
                    for (int j : from[i]) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
            ans[qi] = f[n - 1];
        }
        return ans;
    }


}
