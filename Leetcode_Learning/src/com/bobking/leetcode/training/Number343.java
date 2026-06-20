package com.bobking.leetcode.training;

/**
 * @Date: 2026/6/20 15:27
 * @Author: BobKing
 * @Description:
 */
public class Number343 {

    private long mod = 998244353;

    // 快速乘法
    private int multiply(long x, long y) {
        int res = 0;
        x %= mod;
        y %= mod;
        while (y != 0) {
            if ((y & 1L) != 0) {
                // 加法代替乘法，防止越界
                res += x;
                if (res >= mod) {
                    res -= mod;
                }
            }
            y = y >> 1;
            x = x << 1;
            if (x >= mod) {
                x -= mod;
            }
        }
        return res;
    }

    // 快速幂
    private int Pow(long x, long y) {
        int res = 1;
        while (y != 0) {
            if ((y & 1L) != 0) {
                // 使用fast()计算是因为在该方法中进行了越界处理, 若用 res *= x 则还需要单独进行处理越界
                // 在cutRope()也使用了fast()计算, 因此越界处理统一放在fast()中
                res = multiply(res, x);
            }
            x = multiply(x, x);
            y = y >> 1;
        }
        return res;
    }

    public int integerBreak1(int n) {
        if (n <= 3) {
            return n - 1;
        }
        if (n % 3 == 0) {
            return Pow(3, n / 3);
        } else if (n % 3 == 1) {
            return multiply(Pow(3, n / 3 - 1), 4);
        } else {
            return multiply(Pow(3, n / 3), 2);
        }
    }

    public int integerBreak2(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int a = n / 3;
        int b = n % 3;
        if (b == 0) {
            return (int) Math.pow(3, a);
        }
        if (b == 1) {
            return (int) Math.pow(3, a - 1) * 4;
        }
        return (int) Math.pow(3, a) * 2;
    }

}
