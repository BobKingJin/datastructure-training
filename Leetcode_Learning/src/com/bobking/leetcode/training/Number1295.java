package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-16 1:18
 */
public class Number1295 {

    public int findNumbers(int[] nums) {

        int res = 0;

        for (int i : nums) {
            if (String.valueOf(i).length() % 2 == 0)
                res++;
        }

        return res;
    }
}
