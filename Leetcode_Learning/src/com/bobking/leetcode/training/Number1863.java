package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-08 21:39
 */
public class Number1863 {

    int res = 0;

    public int subsetXORSum(int[] nums) {

        if (nums.length == 1)
            return nums[0];

        dfs(nums, 0, 0);
        return res;
    }

    private void dfs(int[] nums, int i, int xorSum) {

        if (i == nums.length) {
            res += xorSum;
            return;
        }

        // 当前位置要
        dfs(nums, i + 1, xorSum ^ nums[i]);
        // 当前位置不要
        dfs(nums, i + 1, xorSum);
    }
}
