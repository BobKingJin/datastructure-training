package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-17 6:49
 */
public class Number1155 {

    int mod = (int) 1e9 + 7;

    // 参考：https://leetcode.cn/problems/number-of-dice-rolls-with-target-sum/solution/dong-tai-gui-hua-bei-bao-wen-ti-yun-yong-axtf/
    public int numRollsToTarget1(int n, int k, int target) {

        // f[i][j] 表示考虑前 i 个物品组，凑成价值为 j 的方案数
        int[][] f = new int[n + 1][target + 1];
        f[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                for (int l = 1; l <= k; l++) {
                    if (j >= l)
                        f[i][j] = (f[i][j] + f[i - 1][j - l]) % mod;
                }
            }
        }
        return f[n][target];
    }

    // 参考：https://leetcode.cn/problems/number-of-dice-rolls-with-target-sum/solution/dong-tai-gui-hua-bei-bao-wen-ti-yun-yong-axtf/
    public int numRollsToTarget2(int n, int k, int target) {

        int[][] f = new int[2][target + 1];
        f[0][0] = 1;

        for (int i = 1; i <= n; i++) {

            int a = i & 1;
            int b = (i - 1) & 1;
            for (int j = 0; j <= target; j++) {
                // 先手动置零
                f[a][j] = 0;
                for (int l = 1; l <= k; l++) {
                    if (j >= l) {
                        f[a][j] = (f[a][j] + f[b][j - l]) % mod;
                    }
                }
            }
        }
        return f[n & 1][target];
    }

}
