package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-04-02 10:40
 */
public class Number1039 {

    private int[] values;
    private int[][] memo;

    // 参考：https://leetcode.cn/problems/minimum-score-triangulation-of-polygon/solution/shi-pin-jiao-ni-yi-bu-bu-si-kao-dong-tai-aty6/
    public int minScoreTriangulation(int[] values) {

        this.values = values;
        int n = values.length;
        memo = new int[n][n];

        for (int i = 0; i < n; ++i)
            // -1 表示没有访问过
            Arrays.fill(memo[i], -1);
        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        // 只有两个点，无法组成三角形
        if (i + 1 == j)
            return 0;
        if (memo[i][j] != -1)
            return memo[i][j];
        int res = Integer.MAX_VALUE;
        // 枚举顶点 k
        for (int k = i + 1; k < j; ++k)
            res = Math.min(res, dfs(i, k) + dfs(k, j) + values[i] * values[j] * values[k]);
        return memo[i][j] = res;
    }
}
