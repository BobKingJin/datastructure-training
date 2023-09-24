package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-26 13:53
 */
public class Number1899 {

    public boolean mergeTriplets(int[][] triplets, int[] target) {

        boolean[] found  = new boolean[3];

        for(int[] p : triplets) {
            if (p[0] <= target[0] && p[1] <= target[1] && p[2] <= target[2]) {
                found[0] = found[0] || p[0] == target[0];
                found[1] = found[1] || p[1] == target[1];
                found[2] = found[2] || p[2] == target[2];
            }
        }

        return found[0] && found[1] && found[2];
    }
}
