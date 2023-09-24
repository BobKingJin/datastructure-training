package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-02-10 10:12
 */
public class Number1223 {

    final int MOD = 1000000007;

    // 参考：https://leetcode.cn/problems/dice-roll-simulation/solution/ni-bi-dong-chao-jian-dan-dong-tai-gui-hua-fu-za-du/
    public int dieSimulator(int n, int[] rollMax) {

        int[][][] d = new int[n + 1][6][16];
        for (int j = 0; j < 6; j++)
            d[1][j][1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 1; k <= rollMax[j]; k++) {
                    for (int p = 0; p < 6; p++) {
                        if (p != j) {
                            d[i][p][1] = (d[i][p][1] + d[i - 1][j][k]) % MOD;
                        } else if (k + 1 <= rollMax[j]) {
                            d[i][p][k + 1] = (d[i][p][k + 1] + d[i - 1][j][k]) % MOD;
                        }
                    }
                }
            }
        }

        int res = 0;
        for (int j = 0; j < 6; j++) {
            for (int k = 1; k <= rollMax[j]; k++)
                res = (res + d[n][j][k]) % MOD;
        }
        return res;
    }
}
