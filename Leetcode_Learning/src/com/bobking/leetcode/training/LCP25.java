package com.bobking.leetcode.training;

/**
 * @Date: 2023/12/10 7:54
 * @Author: BobKing
 * @Description:
 */
public class LCP25 {

    private final int mod = (int) 1e9 + 7;

    // 参考: https://leetcode.cn/problems/Uh984O/solutions/420058/dong-tai-gui-hua-by-davidfffan-2/
    public int keyboard(int k, int n) {

        long[][] dp = new long[n + 1][27];
        long[][] C = new long[26 * k + 1][k + 1];
        C[0][0] = 1;

        for (int i = 1; i < C.length; ++i) {
            for (int j = 0; j <= Math.min(k, i); ++j) {
                // 对于 j 位置: 当选它时 C[i - 1][j - 1], 不选 j 位置 C[i - 1][j]
                // 对于 j 位置分析这有两种情况都有可能因此等于这两种之和
                if (j == 0) {
                    C[i][j] = 1;
                } else {
                    C[i][j] = C[i - 1][j] + C[i - 1][j - 1];
                }
            }
        }
        // dp[i][j] 表示前面 i 个位置，考虑了前 j 种字母组成的 i 长度的字符串的种数
        // C[i][x] 表示从 i 个位置中选出 x 个位置的组合种数
        for (int i = 0; i <= 26; ++i)
            dp[0][i] = 1;

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= 26; ++j) {
                for (int x = 0; x <= k && x <= i; ++x) {
                    // 重点: 前 i 个位置考虑 j 个字母组成可以分为
                    // 新增的那一个字母可以放在 C[i][x] 个位置上, 也就是从 i 个位置中选取 x 个位置的组合数 * 原来考虑 j - 1个字母组成 i - x个位置的组合数
                    dp[i][j] += dp[i - x][j - 1] * C[i][x];
                }
                dp[i][j] %= mod;
            }
        }
        return (int) dp[n][26];
    }
}
