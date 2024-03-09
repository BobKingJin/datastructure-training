package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2024/3/2 22:39
 * @Author: BobKing
 * @Description:
 */
public class Number2368 {

    public int reachableNodes(int n, int[][] edges, int[] restricted) {

        boolean[] isRestricted = new boolean[n];
        for (int x : restricted)
            isRestricted[x] = true;

        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<Integer>());

        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            if (!isRestricted[x] && !isRestricted[y]) {
                g[x].add(y);
                g[y].add(x);
            }
        }
        return dfs(0, -1, g);
    }

    private int dfs(int x, int fa, List<Integer>[] g) {
        int cnt = 1;
        for (int y : g[x]) {
            if (y != fa)
                cnt += dfs(y, x, g);
        }
        return cnt;
    }

}
