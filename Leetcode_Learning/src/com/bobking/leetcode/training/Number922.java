package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-05-22 18:15
 */
public class Number922 {

    // 参考：程序猿代码指南P396
    public int[] sortArrayByParityII(int[] nums) {

        if (nums == null || nums.length < 1)
            return nums;

        int even = 0;
        int odd = 1;
        int end = nums.length - 1;

        while (even <= end && odd <= end) {

            if ((nums[end] & 1) == 0) {
                swap(nums, even, end);
                even += 2;
            } else {
                swap(nums, odd, end);
                odd += 2;
            }
        }

        return nums;
    }

    public void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
