package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-04-28 8:37
 */
public class Number903 {

    // 参考：https://leetcode.cn/problems/valid-permutations-for-di-sequence/solution/di-xu-lie-de-you-xiao-pai-lie-by-leetcode/
    public int numPermsDISequence(String s) {

        int MOD = 1000000007;
        int N = s.length();

        // dp[i][j] : Num ways to place P_i with relative rank j
        int[][] dp = new int[N + 1][N + 1];
        Arrays.fill(dp[0], 1);

        for (int i = 1; i <= N; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (s.charAt(i - 1) == 'D') {
                    for (int k = j; k < i; ++k) {
                        dp[i][j] += dp[i - 1][k];
                        dp[i][j] %= MOD;
                    }
                } else {
                    for (int k = 0; k < j; ++k) {
                        dp[i][j] += dp[i - 1][k];
                        dp[i][j] %= MOD;
                    }
                }
            }
        }

        int ans = 0;
        for (int x : dp[N]) {
            ans += x;
            ans %= MOD;
        }

        return ans;
    }
}
