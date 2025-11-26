package com.bobking.leetcode.training;

public class Number55 {

    // 参考：程序猿代码指南P247
    public boolean canJump(int[] nums) {

        if (nums == null || nums.length < 1) {
            return false;
        }

        int mostFarDistance = 0;

        for (int i = 0; i < nums.length; i++) {
            // 如果当前位置可以到达
            if (i <= mostFarDistance) {
                mostFarDistance = Math.max(mostFarDistance, i + nums[i]);
            } else {
                return false;
            }
            if (mostFarDistance >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}
