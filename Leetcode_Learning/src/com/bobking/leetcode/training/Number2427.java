package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-05 0:11
 */
public class Number2427 {

    // 参考：https://leetcode.cn/problems/number-of-common-factors/solution/mei-ju-yin-zi-by-endlesscheng-v3fb/
    public int commonFactors(int a, int b) {

        int ans = 0;
        int g = gcd(a, b);

        for (int i = 1; i * i <= g; ++i) {
            if (g % i == 0) {
                // i 是公因子
                ++ans;
                // g / i 是公因子
                if (i * i < g)
                    ++ans;
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }
}
