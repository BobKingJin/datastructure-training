package com.bobking.leetcode.training;

/**
 * @Date: 2026/6/22 10:54
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi53 {

    // 参考: Number34
    public int GetNumberOfK(int[] nums, int k) {
        return bisearch(nums, k + 0.5) - bisearch(nums, k - 0.5);
    }

    private int bisearch(int[] data, double k) {
        int left = 0;
        int right = data.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (data[mid] < k) {
                left = mid + 1;
            } else if (data[mid] > k) {
                right = mid - 1;
            }
        }
        return left;
    }

}
