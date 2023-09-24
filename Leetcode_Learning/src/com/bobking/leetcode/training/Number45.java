package com.bobking.leetcode.training;

public class Number45 {

    // 参考：程序猿代码指南P247
    public int jump(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        int jump = 0;
        int cur = 0;
        int next = 0;

        for (int i = 0; i < nums.length; i++) {
            // 如果当前位置不可以到达
            if (cur < i) {
                jump++;
                cur = next;
            }
            // 每一步都更新，保持最大值
            next = Math.max(next, i + nums[i]);
        }

        return jump;
    }
}
