package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-09-20 7:54
 */
public class LCP06 {

    public int minCount(int[] coins) {

        int count = 0;
        for (int coin : coins)
            count += (coin >> 1) + (coin & 1);

        return count;
    }
}
