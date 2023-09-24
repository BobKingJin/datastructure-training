package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-04-04 22:41
 */
public class Number1000 {

    private int[][][] memo;
    private int[] s;
    private int k;

    // 参考：https://leetcode.cn/problems/minimum-cost-to-merge-stones/solution/tu-jie-qu-jian-dpzhuang-tai-she-ji-yu-yo-ppv0/
    public int mergeStones(int[] stones, int k) {

        int n = stones.length;

        // 无法合并成一堆
        if ((n - 1) % (k - 1) > 0)
            return -1;

        s = new int[n + 1];
        for (int i = 0; i < n; i++)
            // 前缀和
            s[i + 1] = s[i] + stones[i];
        this.k = k;
        memo = new int[n][n][k + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j)
                Arrays.fill(memo[i][j], -1);
        }

        return dfs(0, n - 1, 1);
    }

    // dfs(i, j, p) 表示把 stones[i] 到 stones[j] 合并成 p 堆的最小成本
    private int dfs(int i, int j, int p) {

        if (memo[i][j][p] != -1)
            return memo[i][j][p];
        // 合并成一堆
        if (p == 1)
            return memo[i][j][p] = i == j ? 0 : dfs(i, j, k) + s[j + 1] - s[i];
        int res = Integer.MAX_VALUE;
        // 枚举哪些石头堆合并成第一堆
        for (int m = i; m < j; m += k - 1)
            res = Math.min(res, dfs(i, m, 1) + dfs(m + 1, j, p - 1));
        return memo[i][j][p] = res;
    }

}
