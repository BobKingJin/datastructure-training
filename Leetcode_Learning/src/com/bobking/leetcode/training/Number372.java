package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-06-10 23:11
 */
public class Number372 {

    private int MOD = 1337;

    // 参考：https://leetcode.cn/problems/super-pow/solution/gong-shui-san-xie-di-gui-kuai-su-mi-ying-yx1j/
    public int superPow(int a, int[] b) {
        return dfs(a, b, b.length - 1);
    }

    private int dfs(int a, int[] b, int u) {

        if (u == -1)
            return 1;

        return qpow(dfs(a, b, u - 1), 10) * qpow(a, b[u]) % MOD;
    }

    // a 的 b 次方
    private int qpow(int a, int b) {

        int ans = 1;
        a %= MOD;
        while (b != 0) {

            if ((b & 1) != 0)
                ans = ans * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }

        return ans;
    }

}
