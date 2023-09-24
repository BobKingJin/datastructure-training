package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-09 21:14
 */
public class Number400 {

    // 参考：https://leetcode.cn/problems/nth-digit/solution/gong-shui-san-xie-jian-dan-mo-ni-ti-by-a-w5wl/
    public int findNthDigit(int n) {

        int len = 1;
        while (len * 9 * Math.pow(10, len - 1) < n) {
            n -= len * 9 * Math.pow(10, len - 1);
            len++;
        }

        long s = (long) Math.pow(10, len - 1);
        long x = n / len - 1 + s;
        n -= (x - s + 1) * len;
        return n == 0 ? (int) (x % 10) : (int) ((x + 1) / Math.pow(10, len - n) % 10);
    }
}
