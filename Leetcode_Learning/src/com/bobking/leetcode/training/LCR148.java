package com.bobking.leetcode.training;

import java.util.Stack;

/**
 * @Date: 2024/11/4 10:16
 * @Author: BobKing
 * @Description:
 */
public class LCR148 {

    public boolean validateBookSequences(int[] putIn, int[] takeOut) {
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0;
        for (int num : putIn) {
            stack.push(num);
            while (!stack.isEmpty() && stack.peek() == takeOut[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }
}
