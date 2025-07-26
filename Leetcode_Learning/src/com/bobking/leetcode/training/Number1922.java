package com.bobking.leetcode.training;

/**
 * @Date: 2025/7/26 16:40
 * @Author: BobKing
 * @Description:
 */
public class Number1922 {

    private static final int MOD = 1_000_000_007;

    // 参考: https://leetcode.cn/problems/count-good-numbers/solutions/857728/cheng-fa-yuan-li-kuai-su-mi-by-endlessch-btkn/?envType=daily-question&envId=2025-07-23
    public int countGoodNumbers(long n) {
        return (int) (pow(5, (n + 1) / 2) * pow(4, n / 2) % MOD);
    }

    private long pow(long x, long n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) > 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
            n >>= 1;
        }
        return res;
    }


}
