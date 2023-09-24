package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2023-03-26 13:38
 */
public class Number2395 {

    public boolean findSubarrays(int[] nums) {

        Set<Integer> vis = new HashSet<Integer>();

        for (int i = 1; i < nums.length; ++i) {
            if (!vis.add(nums[i - 1] + nums[i]))
                return true;
        }
        return false;
    }
}
