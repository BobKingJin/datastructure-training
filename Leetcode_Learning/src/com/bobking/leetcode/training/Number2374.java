package com.bobking.leetcode.training;

/**
 * @Date: 2024/3/17 9:56
 * @Author: BobKing
 * @Description:
 */
public class Number2374 {

    public int edgeScore(int[] edges) {

        int len = edges.length;
        long[] count = new long[len];
        int res = 0;
        long max = Integer.MIN_VALUE;

        for (int i = 0; i < len; i++)
            count[edges[i]] += i;

        for (int i = 0; i < len; i++) {
            if (count[i] > max) {
                res = i;
                max = count[i];
            }
        }
        return res;
    }
}
