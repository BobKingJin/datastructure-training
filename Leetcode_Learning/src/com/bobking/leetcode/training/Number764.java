package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-09 10:18
 */
public class Number764 {

    // 参考：https://leetcode.cn/problems/largest-plus-sign/solution/javadong-tai-gui-hua-jian-dan-yi-dong-by-dan-huang/
    public int orderOfLargestPlusSign(int n, int[][] mines) {

        int[][][] dp = new int[n][n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                for (int k = 0; k < 4; k++)
                    dp[i][j][k] = 1;
        }
        for (int[] z : mines) {
            for (int k = 0; k < 4; k++)
                dp[z[0]][z[1]][k] = 0;
        }

        // 左和上
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (dp[i][j][0] == 0)
                    continue;
                dp[i][j][0] = 1 + dp[i][j - 1][0];
                dp[i][j][1] = 1 + dp[i - 1][j][1];
            }
        }

        // 右和下
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                if (dp[i][j][2] == 0)
                    continue;
                dp[i][j][2] = 1 + dp[i][j + 1][2];
                dp[i][j][3] = 1 + dp[i + 1][j][3];
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int k = min(dp[i][j][0], dp[i][j][1], dp[i][j][2], dp[i][j][3]);
                ans = Math.max(ans, k);
            }
        }
        return ans;
    }

    private int min(int a, int b, int c, int d) {
        return Math.min(Math.min(a, b), Math.min(c, d));
    }
    
}
