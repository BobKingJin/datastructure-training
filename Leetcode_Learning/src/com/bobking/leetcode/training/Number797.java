package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-08-05 15:51
 */
public class Number797 {

    int[][] g;
    int n;
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    List<Integer> cur = new ArrayList<Integer>();

    // 参考：https://leetcode.cn/problems/all-paths-from-source-to-target/solution/gong-shui-san-xie-yun-yong-dfs-bao-sou-s-xlz9/
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        g = graph;
        n = g.length;
        cur.add(0);
        dfs(0);
        return ans;
    }

    private void dfs(int u) {

        if (u == n - 1) {
            ans.add(new ArrayList<Integer>(cur));
            return;
        }

        for (int next : g[u]) {
            cur.add(next);
            dfs(next);
            cur.remove(cur.size() - 1);
        }
    }
}
