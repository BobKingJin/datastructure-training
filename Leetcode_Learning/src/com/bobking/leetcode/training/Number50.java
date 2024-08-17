package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-04-04 9:25
 */
public class Number50 {

    // 参考：https://leetcode-cn.com/problems/powx-n/solution/powx-n-by-leetcode-solution/
    public double myPow1(double x, int n) {

        double res = 1.0;
        int exp;

        if (n > 0) {
            exp = n;
        } else if (n < 0) {
            if (x == 0) {
                throw new RuntimeException("0的负次幂不存在");
            } else {
                exp = -n;
            }
        } else {
            return 1;
        }
        // 例如：3 的 10 次方  10 的二进制为：1010 即 3 的 10 次方 = 3 的 8 次方 * 3 的 2 次方
        while (exp != 0) {
            if ((exp & 1) == 1)
                res *= x;
            x *= x;
            exp = exp >>> 1;
        }
        return n > 0 ? res : 1.0 / res;
    }

    // 参考：https://leetcode-cn.com/problems/powx-n/solution/powx-n-by-leetcode-solution/
    public double myPow2(double x, int n) {
        return n > 0 ? quickMul(x, n) : 1.0 / quickMul(x, -n);
    }

    private double quickMul(double x, int n) {

        if (n == 0)
            return 1.0;

        double y = quickMul(x, n / 2);
        // 如果 n 为偶数，那么 x 的 n 次方 = y 的 2 次方   如果 n 为奇数，那么 x 的 n 次方 = y 的 2 次方 * x
        return n % 2 == 0 ? y * y : y * y * x;
    }
}
