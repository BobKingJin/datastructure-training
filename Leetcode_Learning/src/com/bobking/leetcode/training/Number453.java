package com.bobking.leetcode.training;

public class Number453 {

    // 参考：https://leetcode.cn/problems/minimum-moves-to-equal-array-elements/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-tt3zu/
    public int minMoves(int[] nums) {

        int n = nums.length;
        long min = nums[0];
        long sum = 0;

        for (int i : nums) {
            min = Math.min(min, i);
            sum += i;
        }

        return (int)(sum - min * n);
    }
}
