package com.bobking.leetcode.training;

import java.util.Iterator;

/**
 * @author BobKing
 * @create 2023-10-12 7:39
 */
public class Number284 {

    // 参考: https://leetcode.cn/problems/peeking-iterator/
    private class PeekingIterator implements Iterator<Integer> {

        Iterator<Integer> iter;
        Integer next;

        public PeekingIterator(Iterator<Integer> iterator) {
            iter = iterator;
            if (iter.hasNext())
                next = iter.next();
        }

        public Integer peek() {
            return next;
        }

        @Override
        public Integer next() {
            Integer ans = next;
            next = iter.hasNext() ? iter.next() : null;
            return ans;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }
    }
}
