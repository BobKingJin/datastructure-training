package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-10-21 10:08
 */
public class Number1937 {

    // 参考：https://leetcode.cn/problems/maximum-number-of-points-with-cost/solution/dp-you-hua-ji-qiao-chai-xiang-qian-hou-z-5vvc/
    public long maxPoints(int[][] points) {

        // 定义 f[i][j] 表示前 i 行中，第 i 行选择 points[i][j] 时的最大得分，通过枚举上一行的转移来源 k
        // f[i][j] = points[i][j] + maxf[i − 1][k] − ∣k − j∣
        // f[i][j] = points[i][j] − j + max(f[i − 1][k] + k) k ≤ j
        // f[i][j] = points[i][j] + j + max(f[i − 1][k] − k) k > j
        // 由上式可知，在计算 f[i][j] 时，需要知道位置 j 左侧的 f[i - 1][k] + k 的最大值
        // 以及位置 j 右侧的 f[i-1][k] - k 的最大值
        // 这可以在计算完一整行 f[i-1] 之后，在计算下一行 f[i] 之前，预处理出来
        // 代码实现时，f 的第一维可以压缩掉，且预处理过程可以只处理 f[i - 1][k] - k 的最大值
        // f[i - 1][k] + k 的最大值可以一边遍历 points[i] 一边计算
        int m = points.length;
        int n = points[0].length;
        long[][] f = new long[m][n];
        for (int j = 0; j < n; ++j)
            f[0][j] = points[0][j];

        for (int i = 1; i < m; ++i) {
            long ret = f[i - 1][0] + 0;
            for (int j = 0; j < n; ++j) {
                ret = Math.max(ret, f[i - 1][j] + j);
                f[i][j] = points[i][j] - j + ret;
            }

            ret = f[i - 1][n - 1] - (n - 1);
            for (int j = n - 2; j >= 0; --j) {
                f[i][j] = Math.max(f[i][j], points[i][j] + j + ret);
                ret = Math.max(ret, f[i - 1][j] - j);
            }
        }

        long ans = 0;
        for (int j = 0; j < n; ++j)
            ans = Math.max(ans, f[m - 1][j]);

        return ans;
    }
}
