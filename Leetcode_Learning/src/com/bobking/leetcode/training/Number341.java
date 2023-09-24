package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-04-26 22:24
 */
public class Number341 {

    public interface NestedInteger {

         // @return true if this NestedInteger holds a single integer, rather than a nested list.
         public boolean isInteger();

         // @return the single integer that this NestedInteger holds, if it holds a single integer
         // Return null if this NestedInteger holds a nested list
         public Integer getInteger();

         // @return the nested list that this NestedInteger holds, if it holds a nested list
          // Return empty list if this NestedInteger holds a single integer
         public List<NestedInteger> getList();
    }

    public class NestedIterator1 implements Iterator<Integer> {

        Deque<Integer> queue = new ArrayDeque<Integer>();

        public NestedIterator1(List<NestedInteger> nestedList) {
            dfs(nestedList);
        }

        @Override
        public Integer next() {
            return hasNext() ? queue.pollFirst() : -1;
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        private void dfs(List<NestedInteger> list) {
            for (NestedInteger item : list) {
                if (item.isInteger()) {
                    queue.addLast(item.getInteger());
                } else {
                    dfs(item.getList());
                }
            }
        }
    }

    public class NestedIterator2 implements Iterator<Integer> {

        Deque<NestedInteger> stack = new ArrayDeque<NestedInteger>();

        public NestedIterator2(List<NestedInteger> list) {
            for (int i = list.size() - 1; i >= 0; i--) {
                NestedInteger item = list.get(i);
                stack.addLast(item);
            }
        }

        @Override
        public Integer next() {
            return hasNext() ? stack.pollLast().getInteger() : -1;
        }

        @Override
        public boolean hasNext() {
            if (stack.isEmpty()) {
                return false;
            } else {
                NestedInteger item = stack.peekLast();
                if (item.isInteger()) {
                    return true;
                } else {
                    item = stack.pollLast();
                    List<NestedInteger> list = item.getList();
                    for (int i = list.size() - 1; i >= 0; i--)
                        stack.addLast(list.get(i));
                    return hasNext();
                }
            }
        }
    }
}
