package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @Date: 2025/7/26 16:48
 * @Author: BobKing
 * @Description:
 */
public class Number3487 {

    public int maxSum(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int s = 0;
        int mx = Integer.MIN_VALUE;
        for (int x : nums) {
            if (x < 0) {
                mx = Math.max(mx, x);
            } else if (set.add(x)) {
                s += x;
            }
        }
        return set.isEmpty() ? mx : s;
    }

}
