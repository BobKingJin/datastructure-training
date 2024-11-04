package com.bobking.leetcode.training;

/**
 * @Date: 2024/11/4 22:53
 * @Author: BobKing
 * @Description:
 */
public class LCR172 {

    public int countTarget(int[] scores, int target) {
        return solve(scores, target) - solve(scores, target - 1);
    }

    private int solve(int[] scores, int tar) {
        int i = 0;
        int j = scores.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (scores[m] <= tar) {
                i = m + 1;
            } else {
                j = m - 1;
            }
        }
        return i;
    }

}
