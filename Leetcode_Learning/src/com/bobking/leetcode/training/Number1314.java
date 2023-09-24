package com.bobking.leetcode.training;

public class Number1314 {

    // 参考：https://leetcode.cn/problems/matrix-block-sum/solution/ju-zhen-qu-yu-he-by-leetcode-solution/
    public int[][] matrixBlockSum(int[][] mat, int k) {

        int n = mat.length;
        int m = mat[0].length;
        // 用数组 dp 表示数组 mat 的二维前缀和，dp 的维数为 (m + 1) * (n + 1)
        // 其中 dp[i][j] 表示数组 mat 中以 (0, 0) 为左上角，(i - 1, j - 1) 为右下角的矩形子数组的元素之和
        // 题目需要对数组 mat 中的每个位置，计算以 (i - K, j - K) 为左上角，(i + K, j + K) 为右下角的矩形子数组的元素之和
        // 可以在前缀和数组的帮助下，通过：
        // sum = P[i + K + 1][j + K + 1] - P[i - K][j + K + 1] - P[i + K + 1][j - K] + P[i - K][j - K]

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + mat[i - 1][j - 1] - dp[i - 1][j - 1];
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int x1 = i - k;
                int y1 = j - k;
                int x2 = i + k;
                int y2 = j + k;
                if (x1 < 1)
                    x1 = 1;
                if (y1 < 1)
                    y1 = 1;
                if (x2 > n)
                    x2 = n;
                if (y2 > m)
                    y2 = m;
                mat[i - 1][j - 1] = dp[x2][y2] + dp[x1 - 1][y1 - 1] - dp[x1 - 1][y2] - dp[x2][y1 - 1];
            }
        }

        return mat;
    }
}
