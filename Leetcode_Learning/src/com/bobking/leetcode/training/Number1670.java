package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author BobKing
 * @create 2022-06-26 14:45
 */
public class Number1670 {

    // 参考：https://leetcode.cn/problems/design-front-middle-back-queue/solution/java-shuang-deque-by-thedesalizes/
    private class FrontMiddleBackQueue {

        Deque<Integer> left;
        Deque<Integer> right;

        public FrontMiddleBackQueue() {
            left = new ArrayDeque<Integer>();
            right = new ArrayDeque<Integer>();
        }

        public void pushFront(int val) {
            left.addFirst(val);
            reBalance();
        }

        public void pushMiddle(int val) {
            if (left.size() == right.size()) {
                right.addFirst(val);
            } else {
                left.addLast(val);
            }
        }

        public void pushBack(int val) {
            right.addLast(val);
            reBalance();
        }

        public int popFront() {
            Integer integer = left.pollFirst();
            if (integer == null) {
                integer = right.pollFirst();
                return integer == null ? -1 : integer;
            } else {
                reBalance();
                return integer;
            }
        }

        public int popMiddle() {
            if (left.size() == right.size()) {
                Integer integer = left.pollLast();
                return integer == null ? -1 : integer;
            } else {
                return right.pollFirst();
            }
        }

        public int popBack() {
            Integer integer = right.pollLast();
            reBalance();
            return integer == null ? -1 : integer;
        }

        private void reBalance() {
            if (left.size() > right.size()) {
                right.addFirst(left.pollLast());
            } else if (right.size() == left.size() + 2) {
                left.addLast(right.pollFirst());
            }
        }
    }
}
