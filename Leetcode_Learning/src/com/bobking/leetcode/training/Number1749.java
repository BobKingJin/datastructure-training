package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-06 11:40
 */
public class Number1749 {

    public int maxAbsoluteSum(int[] nums) {

        int res = 0;
        int maxs = 0;
        int mins = 0;

        for (Integer t : nums){
            if (maxs >= 0) {
                maxs += t;
            } else {
                maxs = t;
            }
            if (mins <= 0) {
                mins += t;
            } else {
                mins = t;
            }
            res = Math.max(res, Math.abs(maxs));
            res = Math.max(res, Math.abs(mins));
        }

        return res;
    }
}
