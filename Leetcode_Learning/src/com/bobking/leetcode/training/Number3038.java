package com.bobking.leetcode.training;

/**
 * @Date: 2024/2/23 0:01
 * @Author: BobKing
 * @Description:
 */
public class Number3038 {

    public int maxOperations(int[] nums) {

        int s = nums[0] + nums[1];
        int ans = 1;
        for (int i = 3; i < nums.length && nums[i - 1] + nums[i] == s; i += 2)
            ans++;

        return ans;
    }
}
