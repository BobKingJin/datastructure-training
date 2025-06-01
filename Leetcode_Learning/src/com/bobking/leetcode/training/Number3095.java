package com.bobking.leetcode.training;

/**
 * @Date: 2025/6/1 11:50
 * @Author: BobKing
 * @Description:
 */
public class Number3095 {

    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int[] bits = new int[30];
        int res = Integer.MAX_VALUE;

        for (int left = 0, right = 0; right < n; right++) {
            for (int i = 0; i < 30; i++) {
                bits[i] += (nums[right] >> i) & 1;
            }
            while (left <= right && calc(bits) >= k) {
                res = Math.min(res, right - left + 1);
                for (int i = 0; i < 30; i++) {
                    bits[i] -= (nums[left] >> i) & 1;
                }
                left++;
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int calc(int[] bits) {
        int ans = 0;
        for (int i = 0; i < bits.length; i++) {
            if (bits[i] > 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }


}
