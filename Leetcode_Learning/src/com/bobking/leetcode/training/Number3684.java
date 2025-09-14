package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @Date: 2025/9/14 14:06
 * @Author: BobKing
 * @Description:
 */
public class Number3684 {

    public int[] maxKDistinct(int[] nums, int k) {
        Arrays.sort(nums);
        int uniques = removeDuplicates(nums);
        int size = Math.min(uniques, k);

        int[] ans = new int[size];
        for (int i = 0; i < size; i++) {
            ans[i] = nums[uniques - 1 - i];
        }
        return ans;
    }

    private int removeDuplicates(int[] nums) {
        int k = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[k++] = nums[i];
            }
        }
        return k;
    }


}
