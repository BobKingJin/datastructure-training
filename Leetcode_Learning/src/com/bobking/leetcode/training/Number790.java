package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-12 10:22
 */
public class Number790 {

    private long MOD = (long) 1e9 + 7;

    // 参考：https://leetcode.cn/problems/domino-and-tromino-tiling/solution/by-endlesscheng-umpp/
    public int numTilings(int n) {

        if (n == 1)
            return 1;

        long[] f = new long[n + 1];
        f[0] = 1;
        f[1] = 1;
        f[2] = 2;
        for (int i = 3; i <= n; ++i)
            // f[n] = 2f[n - 1] + f[n - 3] n >= 3
            f[i] = (f[i - 1] * 2 + f[i - 3]) % MOD;
        return (int) f[n];
    }
}
