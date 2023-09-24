package com.bobking.leetcode.training;

import java.util.Stack;

public class Number232 {

    // 参考：程序猿代码指南P6
    private class MyQueue {

        Stack<Integer> pushStack;
        Stack<Integer> popStack;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            this.pushStack = new Stack<Integer>();
            this.popStack = new Stack<Integer>();
        }

        public void pushToPop() {
            if (popStack.isEmpty()) {
                while (!pushStack.isEmpty())
                    popStack.push(pushStack.pop());
            }
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {

            pushStack.push(x);
            pushToPop();
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            // 有可能此时 popStack 为 null 而 pushStack 不为空 因此先进行一次 pushToPop 的调用
            pushToPop();
            return popStack.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            // 同上
            pushToPop();
            return popStack.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            // 同上
            pushToPop();
            return popStack.isEmpty();
        }
    }
}
