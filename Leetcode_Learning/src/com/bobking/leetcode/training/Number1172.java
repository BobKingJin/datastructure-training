package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2023-05-01 9:04
 */
public class Number1172 {

    // 参考：https://leetcode.cn/problems/dinner-plate-stacks/solution/yu-qi-wei-hu-di-yi-ge-wei-man-zhan-bu-ru-sphs/
    private class DinnerPlates {

        // 栈的容量
        private int capacity;
        // 所有栈
        private List<Deque<Integer>> stacks = new ArrayList<Deque<Integer>>();
        // 最小堆，保存未满栈的下标
        private PriorityQueue<Integer> idx = new PriorityQueue<Integer>();

        public DinnerPlates(int capacity) {
            this.capacity = capacity;
        }

        public void push(int val) {
            if (!idx.isEmpty() && idx.peek() >= stacks.size())
                // 堆中都是越界下标，直接清空
                idx.clear();
            // 所有栈都是满的
            if (idx.isEmpty()) {
                Deque<Integer> st = new ArrayDeque<Integer>();
                st.push(val);
                stacks.add(st);
                // 新的栈没有满
                if (capacity > 1)
                    idx.add(stacks.size() - 1);
            } else { // 还有未满栈
                Deque<Integer> st = stacks.get(idx.peek());
                st.push(val);
                if (st.size() == capacity)
                    idx.poll();
            }
        }

        public int pop() {
            // 等价为 popAtStack 最后一个非空栈
            return popAtStack(stacks.size() - 1);
        }

        public int popAtStack(int index) {
            if (index < 0 || index >= stacks.size() || stacks.get(index).isEmpty())
                // 非法操作
                return -1;
            Deque<Integer> st = stacks.get(index);
            if (st.size() == capacity)
                idx.add(index);
            int val = st.pop();
            // 去掉末尾的空栈（懒删除，堆中下标在 push 时处理）
            while (!stacks.isEmpty() && stacks.get(stacks.size() - 1).isEmpty())
                stacks.remove(stacks.size() - 1);
            return val;
        }
    }
}
