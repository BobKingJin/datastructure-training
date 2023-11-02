package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-30 9:03
 */
public class Number493 {

    private int count;

    public int reversePairs(int[] nums) {

        if (nums == null || nums.length < 2)
            return 0;

        count = 0;
        mergeSort(nums, 0, nums.length - 1);
        return count;
    }

    private void mergeSort(int[] nums, int start, int end) {

        if (start == end)
            return;

        int mid = start + (end - start) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        int i = start;
        int j = mid + 1;
        while (i <= mid && j <= end) {
            if ((long) nums[i] > 2 * (long) nums[j]) {
                count += mid - i + 1;
                j++;
            } else {
                i++;
            }
        }

        int[] tempArr = new int[end - start + 1];
        i = start;
        j = mid + 1;
        int idx = 0;

        while (i <= mid && j <= end)
            tempArr[idx++] = nums[i] < nums[j] ? nums[i++] : nums[j++];

        while (i <= mid)
            tempArr[idx++] = nums[i++];

        while (j <= end)
            tempArr[idx++] = nums[j++];

        for (i = 0, j = start; j <= end; i++, j++)
            nums[j] = tempArr[i];
    }
}
