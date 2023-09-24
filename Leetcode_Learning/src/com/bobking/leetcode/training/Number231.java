package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-05-14 15:36
 */
public class Number231 {

    public boolean isPowerOfTwo1(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public boolean isPowerOfTwo2(int n) {
        // x 二进制表示中最低位 1 表示的值
        // 如果一个数 n 是 2 的幂，那么有 lowbit(n) = n 的性质
        return n > 0 && (n & -n) == n;
    }

}
