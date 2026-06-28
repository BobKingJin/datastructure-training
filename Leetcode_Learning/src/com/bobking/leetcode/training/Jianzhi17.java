package com.bobking.leetcode.training;

/**
 * @Date: 2026/6/28 23:50
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi17 {

    public int[] printNumbers(int n) {
        // 找到该 n + 1 位数的最小数字
        int end = 1;
        for (int i = 1; i <= n; i++) {
            end *= 10;
        }

        int[] res = new int[end - 1];
        for (int i = 1; i < end; i++) {
            res[i - 1] = i;
        }
        return res;
    }

}
