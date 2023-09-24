package com.bobking.leetcode.training;

public class Jianzhi10_2 {

    public int numWays(int n) {

        if (n == 0 || n == 1)
            return 1;

        int prePre = 1;
        int pre = 1;
        int sum = 0;

        for (int i = 2; i <= n; i++) {
            sum = (pre + prePre) % 1000000007;
            prePre = pre;
            pre = sum;
        }

        return sum;
    }
}
