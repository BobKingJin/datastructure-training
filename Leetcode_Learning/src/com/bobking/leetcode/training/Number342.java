package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-05 0:04
 */
public class Number342 {

    // 参考：https://leetcode.cn/problems/power-of-four/solution/4de-mi-by-leetcode-solution-b3ya/
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }
}
