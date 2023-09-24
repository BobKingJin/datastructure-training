package com.bobking.leetcode.training;

public class Number486 {

    // 参考：程序猿代码指南P245
    public boolean PredictTheWinner1(int[] nums) {

        if (nums == null || nums.length == 0)
            return false;

        if (nums.length == 1)
            return true;

        // f(i, j) 表示先拿的人在 i - j 范围内获取的分数
        int first = f(nums, 0, nums.length - 1);
        // s(i, j) 表示后拿的人在 i - j 范围内获取的分数
        int last = s(nums, 0, nums.length - 1);

        return first >= last;
    }

    private int f(int[] nums, int i, int j) {

        if (i == j)
            return nums[i];

        return Math.max(nums[i] + s(nums, i + 1, j), nums[j] + s(nums, i, j - 1));
    }

    private int s(int[] nums, int i, int j) {

        if (i == j)
            return 0;

        return Math.min(f(nums, i + 1, j), f(nums, i, j - 1));
    }

    // 参考：程序猿代码指南P245
    public boolean PredictTheWinner2(int[] nums) {

        if (nums == null || nums.length == 0)
            return false;

        if (nums.length == 1)
            return true;

        int[][] f = new int[nums.length][nums.length];
        int[][] s = new int[nums.length][nums.length];
        for (int j = 0; j < nums.length; j++) {

            f[j][j] = nums[j];
            for (int i = j - 1; i >= 0; i--) {

                f[i][j] = Math.max(nums[i] + s[i + 1][j], nums[j] + s[i][j - 1]);
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
            }
        }

        return f[0][nums.length - 1] >= s[0][nums.length - 1];
    }
}
