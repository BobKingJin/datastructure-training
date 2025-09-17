package com.bobking.leetcode.training;

import java.util.Stack;

public class Number155 {

    // 参考：程序猿代码指南P1
    private class MinStack {

        private Stack<Integer> dataStack;
        private Stack<Integer> minStack;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            this.dataStack = new Stack<Integer>();
            this.minStack = new Stack<Integer>();
        }

        public void push(int val) {
            dataStack.push(val);
            if (minStack.isEmpty() || val < minStack.peek()) {
                minStack.push(val);
            } else {
                minStack.push(minStack.peek());
            }
        }

        public void pop() {
            if (!dataStack.isEmpty()) {
                dataStack.pop();
                minStack.pop();
            }
        }

        public int top() {
            if (!dataStack.isEmpty()) {
                return dataStack.peek();
            }

            throw new RuntimeException("The Stack is empty!");
        }

        public int getMin() {
            if (!minStack.isEmpty()) {
                return minStack.peek();
            }

            throw new RuntimeException("The Stack is empty!");
        }
    }
}
