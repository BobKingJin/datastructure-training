package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-08-12 21:29
 */
public class Number154 {

    // 参考：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/solution/154-find-minimum-in-rotated-sorted-array-ii-by-jyd/
    public int findMin(int[] nums) {

        if (nums == null || nums.length == 0)
            return Integer.MIN_VALUE;

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int mid = (left + right) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                // 可以保证不会错过最小值
                right = right - 1;
            }
        }

        return nums[left];
    }
}
