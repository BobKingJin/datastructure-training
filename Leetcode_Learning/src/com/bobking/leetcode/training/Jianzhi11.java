package com.bobking.leetcode.training;

/**
 * @Date: 2026/6/29 15:27
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi11 {

    // 参考: Number33
    public int minNumberInRotateArray(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            int m = (i + j) / 2;
            // m在左排序数组中，旋转点在 [m+1, j] 中
            if (nums[m] > nums[j]) {
                i = m + 1;
            } else if (nums[m] < nums[j]) { // m在右排序数组中，旋转点在 [i, m]中
                j = m;
            } else {
                j--;
            }
        }

        return nums[i];
    }

}
