package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2022-04-30 12:25
 */
public class Number217 {

    public boolean containsDuplicate(int[] nums) {

        Set<Integer> set = new HashSet<Integer>();
        for (int x : nums) {
            if (!set.add(x))
                return true;
        }
        return false;
    }
}
