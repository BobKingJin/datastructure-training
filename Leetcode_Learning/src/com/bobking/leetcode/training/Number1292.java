package com.bobking.leetcode.training;

public class Number1292 {

    // 参考：https://leetcode.cn/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/solution/qing-xi-tu-jie-mo-neng-de-qian-zhui-he-by-hlxing/
    public int maxSideLength(int[][] mat, int threshold) {

        int m = mat.length;
        int n = mat[0].length;
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++)
                dp[i][j] = mat[i - 1][j - 1] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
        }

        int ans = 0;

        for (int k = 1; k <= Math.min(m, n); k++) {
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i - k < 0 || j - k < 0)
                        continue;

                    int tmp = dp[i][j] - dp[i - k][j] - dp[i][j - k] + dp[i - k][j - k];
                    if (tmp <= threshold)
                        ans = Math.max(ans, k);
                }
            }
        }

        return ans;
    }
}
