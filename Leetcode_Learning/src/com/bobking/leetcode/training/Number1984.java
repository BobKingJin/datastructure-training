package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-07-30 0:13
 */
public class Number1984 {

    // 参考：https://leetcode.cn/problems/minimum-difference-between-highest-and-lowest-of-k-scores/solution/gong-shui-san-xie-pai-xu-hua-dong-chuang-ru6e/
    public int minimumDifference(int[] nums, int k) {

        // 这 k 个元素必然是有序数组中（排序后）的连续段

        Arrays.sort(nums);

        int n = nums.length;

        int ans = nums[k - 1] - nums[0];

        for (int i = k; i < n; i++)
            ans = Math.min(ans, nums[i] - nums[i - k + 1]);

        return ans;
    }

}
