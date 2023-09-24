package com.bobking.leetcode.training;

public class Number1470 {

    public int[] shuffle(int[] nums, int n) {

        int len = nums.length;
        int[] arr = new int[2 * n];
        int index = 0;

        for (int i = 0; i < len; i++) {
            if (i % 2 == 0) {
                arr[i] = nums[index++];
            } else {
                arr[i] = nums[n++];
            }
        }

        return arr;
    }
}
