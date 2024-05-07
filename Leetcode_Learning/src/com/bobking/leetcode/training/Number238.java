package com.bobking.leetcode.training;

public class Number238 {

    // 参考：程序猿代码指南P409
    public int[] productExceptSelf1(int[] nums) {

        if (nums == null || nums.length < 2)
            return nums;

        int[] l = new int[nums.length];
        int[] r = new int[nums.length];

        l[0] = 1;
        for (int i = 1; i < nums.length; i++)
            l[i] = l[i - 1] * nums[i - 1];

        r[nums.length - 1] = 1;
        for (int i = nums.length - 2; i > -1; i--)
            r[i] = r[i + 1] * nums[i + 1];

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            res[i] = l[i] * r[i];

        return res;
    }

    // 参考：程序猿代码指南P409
    public int[] productExceptSelf2(int[] nums) {

        if (nums == null || nums.length < 2)
            return nums;

        int[] res = new int[nums.length];

        res[0] = nums[0];
        for (int i = 1; i < nums.length; i++)
            res[i] = res[i - 1] * nums[i];

        int temp = 1;
        for (int i = nums.length - 1; i > 0; i--) {
            res[i] = res[i - 1] * temp;
            temp = temp * nums[i];
        }

        res[0] = temp;

        return res;
    }
}
