package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-26 6:39
 */
public class Number2461 {

    public long maximumSubarraySum(int[] nums, int k) {

        int[] freq = new int[10_0001];
        int cnt = 0;
        long sum = 0;
        long max = 0;
        for (int i = 0; i < k; i++) {
            if (freq[nums[i]]++ == 0)
                cnt++;
            sum += nums[i];
        }

        for (int i = 0; i < nums.length - k; i++) {

            if (cnt == k)
                max = Math.max(max, sum);
            if (--freq[nums[i]] == 0)
                cnt--;
            if (freq[nums[i + k]]++ == 0)
                cnt++;
            sum += nums[i + k] - nums[i];
        }

        if (cnt == k)
            max = Math.max(max, sum);

        return max;
    }
}
