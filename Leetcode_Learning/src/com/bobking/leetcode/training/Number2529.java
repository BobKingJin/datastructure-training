package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-17 0:10
 */
public class Number2529 {

    public int maximumCount1(int[] nums) {

        int neg = 0;
        int pos = 0;

        for (int x : nums) {
            if (x < 0) {
                neg++;
            } else if (x > 0) {
                pos++;
            }
        }

        return Math.max(neg, pos);
    }

    public int maximumCount2(int[] nums) {

        int n = nums.length;

        // 从左到右第一个小于等于 0 的角标
        int neg = binarySearch(nums, 0, n, 0);
        int pos = binarySearch(nums, neg, n, 1);
        // 从从左到右最后一个大于等于 0 的角标
        return Math.max(neg, n - pos);
    }

    private int binarySearch(int[] arr, int l, int r, int target) {

        while (l < r) {
            int mid = ((r - l) >> 1) + l;
            if (target <= arr[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }
}
