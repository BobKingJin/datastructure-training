package com.bobking.leetcode.training;

import java.util.Deque;
import java.util.LinkedList;

public class Number1856 {

    // 参考：https://leetcode.cn/problems/maximum-subarray-min-product/solution/zi-shu-zu-zui-xiao-cheng-ji-de-zui-da-zh-rq8r/
    public int maxSumMinProduct(int[] nums) {

        int len = nums.length;
        int[] left = new int[len];
        int[] right = new int[len];
        Deque<Integer> stack = new LinkedList<Integer>();
        // 对于每一个 i 找到右侧连续的不小于它的元素，记录最后一个连续的，不小于它的元素对应下标
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                right[stack.pop()] = i - 1;
            // 存放的是下标
            stack.push(i);
        }

        while (!stack.isEmpty())
            right[stack.pop()] = len - 1;

        // 对于每一个 i 找到左侧连续的不小于它的元素，记录最后一个连续的，不小于它的元素对应下标
        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                left[stack.pop()] = i + 1;
            // 存放的是下标
            stack.push(i);
        }

        while (!stack.isEmpty())
            left[stack.pop()] = 0;

        // 前缀和，用 long 来存放，防止相加时溢出
        long[] dp = new long[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++)
            dp[i] = dp[i - 1] + nums[i];

        long result = 0;
        for (int i = 0; i < len; i++)
            //                                   在 left[i] - right[i] 这个范围内，nums[i] 是最小值
            result = Math.max(result, nums[i] * (dp[right[i]] - dp[left[i]] + nums[left[i]]));
        // right[i]为右坐标 left[i]为左坐标，从 left[i] 到 right[i]
        // 所有元素的和应该是 dp[right[i]] - dp[left[i]] + nums[left[i]]
        return (int) (result % 1000000007);
    }
}
