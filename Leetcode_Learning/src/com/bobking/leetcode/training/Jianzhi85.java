package com.bobking.leetcode.training;

/**
 * @Date: 2026/6/16 1:27
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi85 {

    public int[] FindGreatestSumOfSubArray(int[] array) {
        // 记录到下标 i 为止的最大连续子数组和
        int[] dp = new int[array.length];
        dp[0] = array[0];
        int maxsum = dp[0];
        // 用两个角标记住区间
        int left = 0;
        int right = 0;
        // 记录最长的区间
        int resl = 0;
        int resr = 0;
        for (int i = 1; i < array.length; i++) {
            right++;
            dp[i] = Math.max(dp[i - 1] + array[i], array[i]);
            if (dp[i - 1] + array[i] < array[i]) {
                left = right;
            }
            if (dp[i] > maxsum || dp[i] == maxsum && (right - left + 1) > (resr - resl + 1)) {
                maxsum = dp[i];
                resl = left;
                resr = right;
            }
        }
        int[] res = new int[resr - resl + 1];
        for (int i = resl; i <= resr; i++) {
            res[i - resl] = array[i];
        }
        return res;
    }

}
