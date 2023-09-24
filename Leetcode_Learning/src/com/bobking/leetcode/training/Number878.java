package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-25 11:07
 */
public class Number878 {

    int n;
    int a;
    int b;
    int c;

    // 思路参考：https://leetcode.cn/problems/nth-magical-number/solution/er-fen-da-an-rong-chi-yuan-li-by-endless-9j34/
    // 代码参考：https://leetcode.cn/problems/nth-magical-number/solution/by-ac_oier-ln3b/
    public int nthMagicalNumber(int n, int a, int b) {

        this.n = n;
        this.a = a;
        this.b = b;
        // c 为 a 和 b 的最小公倍数
        c = a * b / gcd(a, b);

        long l = 0;
        long r = (long) 1e18;

        while (l < r) {
            long mid = l + r >> 1;
            if (check(mid) >= n) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return (int)(r % 1000000007);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 能被 a 或 b 整除的数的个数 = 能够被 a 整除的数的个数 + 能够被 b 整除的数的个数 - 既能被 a 又能被 b 整除的数的个数
    private long check(long x) {
        return x / a + x / b - x / c;
    }
}
