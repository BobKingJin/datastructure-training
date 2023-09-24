package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-04-30 10:48
 */
public class Number908 {

    // 参考：https://leetcode-cn.com/problems/smallest-range-i/solution/by-ac_oier-7fh0/
    public int smallestRangeI(int[] nums, int k) {

        int max = nums[0];
        int min = nums[0];
        for (int i : nums) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }

        return Math.max(0, max - min - 2 * k);
    }

}
