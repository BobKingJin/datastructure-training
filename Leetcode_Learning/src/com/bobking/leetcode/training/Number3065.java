package com.bobking.leetcode.training;

/**
 * @Date: 2025/6/2 11:32
 * @Author: BobKing
 * @Description:
 */
public class Number3065 {

    public int minOperations(int[] nums, int k) {
        int ans = 0;
        for (int x : nums) {
            if (x < k) {
                ans++;
            }
        }
        return ans;
    }

}
