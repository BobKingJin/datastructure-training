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
                // 注意: currentIndex++
                swap(nums, ++smallIndex, currentIndex++);
            } else {
                // 注意: 这个位置的 currentIndex 角标不能变化
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
