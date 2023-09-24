package com.bobking.leetcode.training;

public class Number136 {

    // 参考：程序猿代码指南P356
    public int singleNumber(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        int res = 0;

        for (int num : nums)
            res ^= num;

        return res;
    }
}
