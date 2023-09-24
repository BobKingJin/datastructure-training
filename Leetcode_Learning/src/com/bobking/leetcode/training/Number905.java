package com.bobking.leetcode.training;

public class Number905 {

    // 可对比程序猿代码指南P394
    public int[] sortArrayByParity(int[] nums) {

        if (nums == null || nums.length == 0)
            return nums;

        // 插入的角标
        int index = -1;

        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 0)
                swap(nums, i, ++index);
        }

        return nums;
    }

    private void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
