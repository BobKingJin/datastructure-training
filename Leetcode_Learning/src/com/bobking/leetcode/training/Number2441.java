package com.bobking.leetcode.training;

import java.util.HashSet;

/**
 * @author BobKing
 * @create 2023-05-14 7:27
 */
public class Number2441 {

    public int findMaxK(int[] nums) {

        int ans = -1;
        HashSet<Integer> s = new HashSet<Integer>();

        for (int x : nums) {
            if (s.contains(-x))
                ans = Math.max(ans, Math.abs(x));
            s.add(x);
        }
        return ans;
    }
}
