package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-19 0:09
 */
public class Number1732 {

    public int largestAltitude(int[] gain) {

        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < gain.length; i++) {
            sum += gain[i];
            max = Math.max(max, sum);
        }

        if (max < 0)
            return 0;

        return max;
    }
}
