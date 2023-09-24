package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-18 1:01
 */
public class Number2270 {

    public int waysToSplitArray(int[] nums) {

        int cnt = 0;
        long total = 0;
        long sum = 0;

        for (int num : nums)
            total += num;

        for (int i = 0; i < nums.length - 1; i++) {
            sum += nums[i];
            if (sum >= total - sum)
                cnt++;
        }
        return cnt;
    }
}
