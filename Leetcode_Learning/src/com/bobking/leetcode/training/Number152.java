package com.bobking.leetcode.training;

public class Number152 {

    // 参考：程序猿代码指南P402
    public int maxProduct(int[] nums) {

        if (nums == null || nums.length < 1)
            return 0;

        int[] max = new int[nums.length];
        int[] min = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            max[i] = nums[i];
            min[i] = nums[i];
        }

        for (int i = 1; i < nums.length; i++) {
            max[i] = Math.max(max[i - 1] * nums[i], Math.max(min[i - 1] * nums[i], nums[i]));
            min[i] = Math.min(min[i - 1] * nums[i], Math.min(max[i - 1] * nums[i], nums[i]));
        }

        int res = max[0];
        for (int i = 1; i < max.length; i++) {
            if (max[i] > res)
                res = max[i];
        }

        return res;
    }
}
