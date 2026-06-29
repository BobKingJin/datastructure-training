package com.bobking.leetcode.training;

/**
 * @Date: 2026/6/29 15:52
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi10 {

    public int Fibonacci(int n) {

        if (n == 1 || n == 2) {
            return 1;
        }

        int prePre = 1;
        int pre = 1;
        int sum = 0;

        for (int i = 3; i <= n; i++) {
            sum = pre + prePre;
            prePre = pre;
            pre = sum;
        }

        return sum;
    }

}
