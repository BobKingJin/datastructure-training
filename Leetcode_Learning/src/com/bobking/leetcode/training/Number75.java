package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-07-22 20:50
 */
public class Number75 {

    public void sortColors(int[] nums) {

        if (nums == null || nums.length == 0)
            return;

        sort(nums, 1);
    }

    private void sort(int[] nums, int target) {

        int smallIndex = -1;
        int largeIndex = nums.length;

        int currentIndex = 0;
        while (currentIndex < largeIndex) {

            if (nums[currentIndex] == target) {
                currentIndex++;
            } else if (nums[currentIndex] < target) {
                swap(nums, ++smallIndex, currentIndex++);
            } else {
                swap(nums, --largeIndex, currentIndex);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
