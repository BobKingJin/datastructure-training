package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-09-24 9:10
 */
public class Number2733 {

    public int findNonMinOrMax(int[] nums) {
        if (nums.length < 3)
            return -1;
        // 只对前三个数排序
        Arrays.sort(nums, 0, 3);
        return nums[1];
    }
}
