package com.bobking.leetcode.training;

import java.util.Stack;

public class Number739 {

    public int[] dailyTemperatures(int[] temperatures) {

        if (temperatures == null || temperatures.length == 0) {
            return temperatures;
        }

        int[] res = new int[temperatures.length];

        // 单调栈，从小到大，存放的是数组的角标
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                res[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }
}
