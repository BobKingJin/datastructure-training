package com.bobking.leetcode.training;

/**
 * @Date: 2024/3/18 23:17
 * @Author: BobKing
 * @Description:
 */
public class Number303 {

    class NumArray {

        private int[] sum;

        public NumArray(int[] nums) {
            sum = new int[nums.length + 1];
            for (int i = 1; i <= nums.length; ++i)
                sum[i] = sum[i - 1] + nums[i - 1];
        }

        public int sumRange(int left, int right) {
            return sum[right + 1] - sum[left];
        }
    }
}
