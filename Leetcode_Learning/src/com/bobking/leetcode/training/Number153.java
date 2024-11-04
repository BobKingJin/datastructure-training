package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-08-12 16:52
 */
public class Number153 {

    // 参考：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/solution/er-fen-cha-zhao-wei-shi-yao-zuo-you-bu-dui-cheng-z/
    // 对比Number33
    public int findMin(int[] nums) {

        if (nums == null || nums.length == 0)
            return Integer.MIN_VALUE;

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }
}
