package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-05-28 16:39
 */
public class Number201 {

    // 参考：https://leetcode.cn/problems/bitwise-and-of-numbers-range/solution/shu-zi-fan-wei-an-wei-yu-by-leetcode-solution/
    public int rangeBitwiseAnd(int left, int right) {

        // 对所有数字执行按位与运算的结果是所有对应二进制字符串的公共前缀再用零补上后面的剩余位
        // 目的是求出两个给定数字的二进制字符串的公共前缀
        int shift = 0;
        // 找到公共前缀
        while (left < right) {
            left >>= 1;
            right >>= 1;
            ++shift;
        }

        return left << shift;
    }
}
