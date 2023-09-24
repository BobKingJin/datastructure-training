package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author BobKing
 * @create 2021-07-24 12:13
 */
public class Number503 {

    // 参考：https://leetcode-cn.com/problems/next-greater-element-ii/solution/dong-hua-jiang-jie-dan-diao-zhan-by-fuxu-4z2g/
    public int[] nextGreaterElements(int[] nums) {

        if (nums == null || nums.length == 0)
            return nums;

        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<Integer>();
        // nums 为一个循环数组
        // 使用取模运算解决循环数组
        for (int i = 0; i < 2 * nums.length; i++) {
            while (!stack.isEmpty() && nums[i % nums.length] > nums[stack.peek()])
                res[stack.pop()] = nums[i % nums.length];
            stack.push(i % nums.length);
        }

        return res;
    }
}
