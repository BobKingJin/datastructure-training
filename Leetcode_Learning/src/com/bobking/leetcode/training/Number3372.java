package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2025/7/19 11:55
 * @Author: BobKing
 * @Description:
 */
public class Number3372 {

    // 参考: https://leetcode.cn/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i/solutions/3006334/nao-jin-ji-zhuan-wan-bao-li-mei-ju-pytho-ua6k/?envType=daily-question&envId=2025-07-13
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int max2 = 0;
        if (k > 0) {
            List<Integer>[] g = buildTree(edges2);
            for (int i = 0; i < edges2.length + 1; i++) {
                max2 = Math.max(max2, dfs(i, -1, 0, g, k - 1));
            }
        }

        List<Integer>[] g = buildTree(edges1);
        int[] ans = new int[edges1.length + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = dfs(i, -1, 0, g, k) + max2;
        }
        return ans;
    }

    private List<Integer>[] buildTree(int[][] edges) {
        List<Integer>[] g = new ArrayList[edges.length + 1];
        Arrays.setAll(g, i -> new ArrayList<Integer>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            g[x].add(y);
            g[y].add(x);
        }
        return g;
    }

    private int dfs(int x, int fa, int d, List<Integer>[] g, int k) {
        if (d > k) {
            return 0;
        }
        int cnt = 1;
        for (int y : g[x]) {
            // fa 为上一次的节点 y != fa 避免陷入死循环
            if (y != fa) {
                // 注意传入的是 d + 1, 而不是 d++
                cnt += dfs(y, x, d + 1, g, k);
            }
        }
        return cnt;
    }

}
