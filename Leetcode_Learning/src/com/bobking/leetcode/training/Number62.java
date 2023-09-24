package com.bobking.leetcode.training;

import java.util.Arrays;

public class Number62 {

    // 参考：https://leetcode-cn.com/problems/unique-paths/solution/dong-tai-gui-hua-by-powcai-2/
    public int uniquePaths1(int m, int n) {

        if (m < 1 || n < 1)
            return 0;

        // dp[i][j] 表示从 (i, j) 到右下角的路径数
        int[][] dp = new int[m][n];
        for (int column = n - 1; column >= 0; column--)
            dp[m - 1][column] = 1;
        for (int row = m - 1; row >= 0; row--)
            dp[row][n - 1] = 1;

        for (int row = m - 2; row >= 0; row--) {
            for (int column = n - 2; column >= 0; column--)
                dp[row][column] = dp[row + 1][column] + dp[row][column + 1];
        }

        return dp[0][0];
    }

    public int uniquePaths5(int m, int n) {

        if (m < 1 || n < 1)
            return 0;

        // dp[i][j] 表示从 (0, 0) 到 (i, j)的路径数
        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        // 第一行
        for(int column = 1; column < n; column++)
            dp[0][column] = 1;
        // 第一列
        for(int row = 1; row < m; row++)
            dp[row][0] = 1;

        for (int row = 1; row < m; row++) {
            for (int column = 1; column < n; column++)
                dp[row][column] = dp[row - 1][column] + dp[row][column - 1];
        }

        return dp[m - 1][n - 1];
    }

    // 参考：https://leetcode-cn.com/problems/unique-paths/solution/dong-tai-gui-hua-by-powcai-2/
    public int uniquePaths2(int m, int n) {

        if (m < 1 || n < 1)
            return 0;

        // 空间优化：由于dp[i][j] = dp[i + 1][j] + dp[i][j + 1]，因此只需要保留当前行与上一行的数据
        // 上一行
        int[] pre = new int[n];
        // 当前行
        int[] cur = new int[n];

        Arrays.fill(pre, 1);
        Arrays.fill(cur, 1);

        for (int row = m - 2; row >= 0; row--) {
            for (int column = n - 2; column >= 0; column--)
                cur[column] = cur[column + 1] + pre[column];
            pre = cur.clone();
        }

        return cur[0];
    }

    // 参考：https://leetcode-cn.com/problems/unique-paths/solution/dong-tai-gui-hua-by-powcai-2/
    public int uniquePaths3(int m, int n) {

        if (m < 1 || n < 1)
            return 0;

        // 空间优化：由于dp[i][j] = dp[i + 1][j] + dp[i][j + 1]，因此只需要保留当前行与上一行的数据
        // cur[j] = cur[j] + cur[j - 1] 等价于 cur[j] = pre[j] + cur[j - 1]
        // 当前行
        int[] cur = new int[n];
        Arrays.fill(cur, 1);

        for (int row = m - 2; row >= 0; row--) {
            for (int column = n - 2; column >= 0; column--)
                cur[column] = cur[column] + cur[column + 1];
        }

        return cur[0];
    }

    // 参考：https://leetcode-cn.com/problems/unique-paths/solution/dong-tai-gui-hua-di-gui-gong-shi-deng-3z-9mp1/
    // 超时
    // dfs
    public int uniquePaths4(int m, int n) {

        if (m < 1 || n < 1)
            return 0;
        // 递归
        int result = recrusion(m, n, 1, 1);
        return result;
    }

    private int recrusion(int m, int n, int row, int column) {

        if (row > m || column > n)
            return 0;

        if (row == m && column == n)
            return 1;

        return recrusion(m, n, row, column + 1) + recrusion(m, n, row + 1, column);
    }
}
