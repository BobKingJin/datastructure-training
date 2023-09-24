package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-30 15:17
 */
public class Number2419 {

    public int longestSubarray(int[] nums) {

        // 根据按位与运算的特性，有 a AND b <= min(a, b)。题目首先要求子数组按位与结果最大，然后求最长子数组
        // 因此题目实际上求的是数组中的最大值最多连续出现了几次。

        int n = nums.length;

        // 求数组中的最大值
        int max = 0;
        for (int x : nums) 
            max = Math.max(max, x);

        // 统计该最大值最多连续出现几次
        int ans = 1;
        int cnt = 0;

        for (int x : nums) {
            if (x == max) {
                cnt++;
            } else {
                ans = Math.max(ans, cnt);
                cnt = 0;
            }
        }
        ans = Math.max(ans, cnt);
        return ans;
    }
}
