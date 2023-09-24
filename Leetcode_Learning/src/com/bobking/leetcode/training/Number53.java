package com.bobking.leetcode.training;

public class Number53 {

    // 参考：程序猿代码指南P397
    public int maxSubArray1(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {

            cur += nums[i];
            // 每一步都有可能成为最大值
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }

        return max;
    }

    public int maxSubArray2(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        // 数组中以 array[i] 结尾的最大连续子序列之和
        int maxSubSumEndOfArrayI = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {

            maxSubSumEndOfArrayI = Math.max(maxSubSumEndOfArrayI + nums[i], nums[i]);
            max = Math.max(max, maxSubSumEndOfArrayI);
        }

        return max;
    }
}
