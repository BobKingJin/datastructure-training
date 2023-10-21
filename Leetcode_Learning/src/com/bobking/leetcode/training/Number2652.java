package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-18 7:33
 */
public class Number2652 {

    // 参考: https://leetcode.cn/problems/sum-multiples/solutions/2241283/o1-rong-chi-yuan-li-by-endlesscheng-yxc4/?envType=daily-question&envId=Invalid%20Date
    public int sumOfMultiples(int n) {
        return arithmeticProgressionSum(n, 3) + arithmeticProgressionSum(n, 5) + arithmeticProgressionSum(n, 7)
                - arithmeticProgressionSum(n, 15) - arithmeticProgressionSum(n, 21) - arithmeticProgressionSum(n, 35)
                + arithmeticProgressionSum(n, 105);
    }

    private int arithmeticProgressionSum(int n, int m) {
        return n / m * (n / m + 1) / 2 * m;
    }

}
