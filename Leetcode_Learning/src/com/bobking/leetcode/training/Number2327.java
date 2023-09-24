package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-12 10:56
 */
public class Number2327 {

    private int MOD = (int) 1e9 + 7;

    // 参考：https://leetcode.cn/problems/number-of-people-aware-of-a-secret/solution/by-endlesscheng-2x0z/
    public int peopleAwareOfSecret(int n, int delay, int forget) {

        int[] f = new int[n];
        f[0] = 1;
        int cntB = 0;

        for (int i = 0; i < n; ++i) {
            if (i + delay >= n)
                cntB = (cntB + f[i]) % MOD;
            for (int j = i + delay; j < Math.min(i + forget, n); ++j)
                f[j] = (f[j] + f[i]) % MOD;
        }

        return (f[n - 1] + cntB) % MOD;
    }
}
