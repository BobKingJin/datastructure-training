package com.bobking.leetcode.training;

/**
 * @Date: 2024/3/15 8:03
 * @Author: BobKing
 * @Description:
 */
public class Number2312 {

    // 参考: https://leetcode.cn/problems/selling-pieces-of-wood/solutions/1611240/by-endlesscheng-mrmd/?envType=daily-question&envId=2024-03-15
    public long sellingWood(int m, int n, int[][] prices) {

        int[][] pr = new int[m + 1][n + 1];

        for (int[] p : prices)
            pr[p[0]][p[1]] = p[2];

        // 定义 f[i][j] 表示对一块高 i 宽 j 的木块
        long[][] f = new long[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                f[i][j] = pr[i][j];
                for (int k = 1; k < j; k++)
                    // 垂直切割
                    f[i][j] = Math.max(f[i][j], f[i][k] + f[i][j - k]);
                for (int k = 1; k < i; k++)
                    // 水平切割
                    f[i][j] = Math.max(f[i][j], f[k][j] + f[i - k][j]);
            }
        }
        return f[m][n];
    }
}
