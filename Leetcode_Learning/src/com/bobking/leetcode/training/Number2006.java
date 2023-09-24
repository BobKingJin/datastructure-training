package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-06-27 7:15
 */
public class Number2006 {

    public int countKDifference1(int[] nums, int k) {

        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(nums[i] - nums[j]) == k)
                    ans++;
            }
        }
        return ans;
    }

    public int countKDifference2(int[] nums, int k) {

        int[] cnts = new int[110];
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int t = nums[i];
            if (t - k >= 1)
                ans += cnts[t - k];
            if (t + k <= 100)
                ans += cnts[t + k];
            cnts[t]++;
        }
        return ans;
    }
}
