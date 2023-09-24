package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-10 16:20
 */
public class Number2460 {

    public int[] applyOperations(int[] nums) {

        int len = nums.length;
        int idx = 0;

        for (int i = 0; i < len - 1; i++) {

            if (nums[i] == 0)
                continue;

            if (nums[i] == nums[i + 1]) {
                nums[i + 1] = 0;
                nums[idx++] = nums[i] * 2;
            } else {
                nums[idx++] = nums[i];
            }
        }

        if (nums[len - 1] != 0)
            nums[idx++] = nums[len - 1];

        for (int i = idx; i < len; i++)
            nums[i] = 0;

        return nums;
    }
}
