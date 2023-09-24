package com.bobking.leetcode.training;

import java.util.Stack;

public class Number739 {

    public int[] dailyTemperatures(int[] temperatures) {

        int[] res = new int[temperatures.length];

        if (temperatures == null || temperatures.length == 0)
            return res;

        // 单调栈，从小到大，存放的是数组的角标
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                res[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }

       // 整型数组的默认值即为 0
       // while (!stack.isEmpty())
       //     res[stack.pop()] = 0;

        return res;
    }
}
