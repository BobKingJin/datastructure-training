package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2024/3/8 22:43
 * @Author: BobKing
 * @Description:
 */
public class Number1617 {

    private List<Integer>[] g;
    private boolean[] inSet;
    private boolean[] vis;
    private int[] ans;
    private int n;
    private int diameter;

    // 参考: https://leetcode.cn/problems/count-subtrees-with-max-distance-between-cities/solutions/2162612/tu-jie-on3-mei-ju-zhi-jing-duan-dian-che-am2n/
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {

        this.n = n;
        this.g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<Integer>());

        for (int[] e : edges) {
            // 编号改为从 0 开始
            int x = e[0] - 1;
            int y = e[1] - 1;
            // 建树
            g[x].add(y);
            g[y].add(x);
        }

        ans = new int[n - 1];
        inSet = new boolean[n];
        f(0);
        return ans;
    }

    private void f(int i) {
        if (i == n) {
            for (int v = 0; v < n; ++v)
                if (inSet[v]) {
                    vis = new boolean[n];
                    diameter = 0;
                    dfs(v);
                    break;
                }
            if (diameter > 0 && Arrays.equals(vis, inSet))
                ++ans[diameter - 1];
            return;
        }

        // 不选城市 i
        f(i + 1);
        // 选城市 i
        inSet[i] = true;
        f(i + 1);
        // 恢复现场
        inSet[i] = false;
    }

    // 求树的直径
    private int dfs(int x) {
        vis[x] = true;
        int maxLen = 0;
        for (int y : g[x]) {
            if (!vis[y] && inSet[y]) {
                int ml = dfs(y) + 1;
                diameter = Math.max(diameter, maxLen + ml);
                maxLen = Math.max(maxLen, ml);
            }
        }
        return maxLen;
    }

}
