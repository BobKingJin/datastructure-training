package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-12-07 12:22
 */
public class Number2220 {

    public int minBitFlips(int start, int goal) {

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if ((start & 1) != (goal & 1))
                ans++;
            start = start >> 1;
            goal = goal >> 1;
        }
        return ans;
    }
}
