package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-02-15 10:28
 */
public class Number1250 {

    // 参考：https://leetcode.cn/problems/check-if-it-is-a-good-array/solution/python3javacgo-yi-ti-yi-jie-shu-xue-pei-3f4da/
    public boolean isGoodArray(int[] nums) {

        int g = 0;
        for (int x : nums)
            g = gcd(x, g);

        return g == 1;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
