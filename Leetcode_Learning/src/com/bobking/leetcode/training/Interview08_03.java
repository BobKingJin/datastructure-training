package com.bobking.leetcode.training;

public class Interview08_03 {

    // 参考：https://leetcode-cn.com/problems/magic-index-lcci/solution/mo-zhu-suo-yin-by-leetcode-solution/
    public int findMagicIndex(int[] nums) {

        if (nums == null || nums.length == 0)
            return -1;

        // getAnswer(nums, left, right) 返回数组 nums 的下标范围 [left, right] 中第一个满足条件的答案
        // 如果没有返回 -1
        return getAnswer(nums, 0, nums.length - 1);
    }

    private int getAnswer(int[] nums, int left, int right) {

        if (left > right)
            // 因为角标范围为 0 - (nums.length - 1)，所以返回为 -1，返回的是一个没有用到的角标
            return -1;

        int mid = left + (right - left) / 2;
        // 因为可能存在多个符合条件的，返回的是索引下标最小的
        // 先 左 后 中间 再 右
        int leftAnswer = getAnswer(nums, left, mid - 1);
        if (leftAnswer != -1) {
            return leftAnswer;
            // nums[mid] == mid
        } else if (nums[mid] == mid) {
            return mid;
        }

        return getAnswer(nums, mid + 1, right);
    }
}
