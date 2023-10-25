package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-10-25 8:18
 */
public class Number561 {

    public int arrayPairSum(int[] nums) {

        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;

        for (int i = 0; i < n; i += 2)
            ans += nums[i];

        return ans;
    }
}
