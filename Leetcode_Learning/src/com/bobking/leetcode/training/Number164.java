package com.bobking.leetcode.training;

public class Number164 {

    // 参考：程序猿代码指南P416
    public int maximumGap(int[] nums) {

        if (nums == null || nums.length < 2)
            return 0;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }

        if (min == max)
            return 0;

        boolean[] hasNum = new boolean[nums.length + 1];
        int[] maxs = new int[nums.length + 1];
        int[] mins = new int[nums.length + 1];
        int bid = 0;
        for (int i = 0; i < nums.length; i++) {
            bid = bucket(nums[i], nums.length, min, max);
            mins[bid] = hasNum[bid] ? (Math.min(mins[bid], nums[i])) : nums[i];
            maxs[bid] = hasNum[bid] ? (Math.max(maxs[bid], nums[i])) : nums[i];
            hasNum[bid] = true;
        }

        int res = 0;
        int lastMax = maxs[0];
        int i = 1;

        for (; i <= nums.length; i++) {

            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }

        return res;
    }

    private int bucket(long num, long len, long min, long max) {
        return (int) ((num - min) * len / (max - min));
    }

}
