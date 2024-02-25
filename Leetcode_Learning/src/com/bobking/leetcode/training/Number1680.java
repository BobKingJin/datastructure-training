package com.bobking.leetcode.training;

/**
 * @Date: 2024/2/25 19:01
 * @Author: BobKing
 * @Description:
 */
public class Number1680 {

    private static final int MOD = 1000000007;

    // 参考: https://leetcode.cn/problems/concatenation-of-consecutive-binary-numbers/solutions/511090/java-beat-100-by-civitas-gwad/
    // 参考: https://leetcode.cn/problems/concatenation-of-consecutive-binary-numbers/solutions/510956/lian-jie-lian-xu-er-jin-zhi-shu-zi-by-ze-t40j/
    public int concatenatedBinary(int n) {

        int res = 0;
        int shift = 0;

        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0)
                // 说明是2的幂，则进位
                shift++;
            res = (int) ((((long) res << shift) + i) % MOD);
        }
        return res;
    }
}
