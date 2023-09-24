package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-06-28 7:37
 */
public class Number1681 {

    public int minimumIncompatibility(int[] nums, int k) {

        int n = nums.length;
        int total = 1 << n;

        int[] value = new int[total];
        for (int i = 0; i < total; i++) {
            if (Integer.bitCount(i) == n / k) {
                int[] freq = new int[n + 1];
                int max = 0;
                int min = 16;
                for (int j = 0; j < n; j++) {
                    if (((i >> j) & 1) == 1) {
                        freq[nums[j]]++;
                        max = Math.max(max, nums[j]);
                        min = Math.min(min, nums[j]);
                    }
                }
                boolean flag = true;
                for (int j = 1; j <= n; j++) {
                    if (freq[j] > 1) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    value[i] = max - min;
                } else {
                    value[i] = -1;
                }
            } else {
                value[i] = -1;
            }
        }

        int[] dp = new int[total];
        for (int i = 1; i < total; i++) {
            dp[i] = -1;
            if (Integer.bitCount(i) % (n / k) == 0) {
                int sub = i;
                while (sub > 0) {
                    if (value[sub] >= 0 && dp[i ^ sub] >= 0) {
                        if (dp[i] == -1) {
                            dp[i] = value[sub] + dp[i ^ sub];
                        } else {
                            dp[i] = Math.min(dp[i], value[sub] + dp[i ^ sub]);
                        }
                    }
                    sub = (sub - 1) & i;
                }
            }
        }
        return dp[total - 1];
    }
}
