package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-06-23 9:57
 */
public class Number1595 {

    private List<List<Integer>> cost;
    private int[] minCost;
    private int[][] memo;

    // 参考：https://leetcode.cn/problems/minimum-cost-to-connect-two-groups-of-points/solution/jiao-ni-yi-bu-bu-si-kao-dong-tai-gui-hua-djxq/
    public int connectTwoGroups(List<List<Integer>> cost) {
        this.cost = cost;
        int n = cost.size();
        int m = cost.get(0).size();
        this.minCost = new int[m];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        for (int j = 0; j < m; j++) {
            for (List<Integer> c : cost)
                minCost[j] = Math.min(minCost[j], c.get(j));
        }

        memo = new int[n][1 << m];
        for (int i = 0; i < n; i++)
            Arrays.fill(memo[i], -1);
        return dfs(n - 1, (1 << m) - 1);
    }

    // 定义 dfs(i, j) 表示第一组的 0, 1, ⋯, i 和第二组的 0, 1, ⋯, (m − 1) 相连，且第二组的集合 j 未被连接时，最小成本是多少
    private int dfs(int i, int j) {
        if (i < 0) {
            int res = 0;
            // 第一组的点全部连完后，第二组的某些点可能未被连接，这些点可以去第一组找个成本最小的点连上
            for (int k = 0; k < minCost.length; k++)
                // 第二组的点 k 未连接
                if (((j >> k) & 1) == 1)
                    // 去第一组找个成本最小的点连接
                    res += minCost[k];
            return res;
        }
        if (memo[i][j] != -1)
            return memo[i][j];
        int res = Integer.MAX_VALUE;
        // 第一组的点 i 与第二组的点 k
        for (int k = 0; k < minCost.length; k++)
            res = Math.min(res, dfs(i - 1, j & ~(1 << k)) + cost.get(i).get(k));
        memo[i][j] = res;
        return res;
    }
}
