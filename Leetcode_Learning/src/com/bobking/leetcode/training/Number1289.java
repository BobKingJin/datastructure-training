package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @Date: 2024/1/1 11:29
 * @Author: BobKing
 * @Description:
 */
public class Number1289 {

    // 参考: https://leetcode.cn/problems/minimum-falling-path-sum-ii/solutions/101728/xia-jiang-lu-jing-zui-xiao-he-ii-by-leetcode-solut/
    public int minFallingPathSum(int[][] grid) {

        int n = grid.length;
        // 状态 dp[i][j] 表示从数组grid 的前 i 行中的每一行选择一个数字，并且第 i 行选择的数字为 grid[i][j] 时，可以得到的路径和最小值
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);

        for (int i = 0; i < n; i++)
            dp[0][i] = grid[0][i];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (j == k)
                        continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + grid[i][j]);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++)
            res = Math.min(res, dp[n - 1][j]);

        return res;
    }
}
