package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-09-18 6:43
 */
public class Number2240 {

    public long waysToBuyPensPencils(int total, int cost1, int cost2) {

        long n = 1 + total / cost1;
        long ans = n;

        for (long i = 0; i < n; i++)
            ans += (total - cost1 * i) / cost2;

        return ans;
    }
}
