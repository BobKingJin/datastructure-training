package com.bobking.leetcode.training;

public class Number162 {

    // 参考：程序猿代码指南P401
    // 参考：https://leetcode-cn.com/problems/find-peak-element/solution/xun-zhao-feng-zhi-by-leetcode/
    public int findPeakElement(int[] nums) {

        if (nums == null || nums.length == 0)
            return -1;

        if (nums.length == 1 || nums[0] > nums[1])
            return 0;

        if (nums[nums.length - 1] > nums[nums.length - 2])
            return nums.length - 1;

        // 去头去尾
        int left = 1;
        int right = nums.length - 2;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid - 1] > nums[mid]) {
                right = mid - 1;
            } else if (nums[mid + 1] > nums[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return left;
    }
}
