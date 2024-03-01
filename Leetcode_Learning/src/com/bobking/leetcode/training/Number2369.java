package com.bobking.leetcode.training;

/**
 * @Date: 2024/3/1 8:21
 * @Author: BobKing
 * @Description:
 */
public class Number2369 {

    public boolean validPartition(int[] nums) {

        int n = nums.length;
        // 定义 f[0] = true, f[i+1] 表示能否有效划分 nums[0] 到 nums[i]
        boolean[] f = new boolean[n + 1];
        f[0] = true;

        for (int i = 1; i < n; i++) {
            if ((f[i - 1] && nums[i] == nums[i - 1]) ||
                    i > 1 && f[i - 2] && ((nums[i] == nums[i - 1] && nums[i] == nums[i - 2]) ||
                            (nums[i] == nums[i - 1] + 1 && nums[i] == nums[i - 2] + 2))) {
                f[i + 1] = true;
            }
        }
        return f[n];
    }
}
