package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-04-30 10:32
 */
public class Jianzhi10_1 {

    public int numWays1(int n) {

        if (n == 0 || n == 1)
            return 1;

        return (numWays1(n - 1) % 1000000007) + (numWays1(n - 2) % 1000000007);
    }

    public int numWays2(int n) {

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
