package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

/**
 * @author BobKing
 * @create 2023-01-18 23:17
 */
public class Number1825 {

    // 参考：https://leetcode.cn/problems/finding-mk-average/solution/by-lcbin-bonb/
    class MKAverage {

        private int m;
        private int k;
        // 维护 mid 中所有元素的和
        private long s;
        private int size1;
        private int size3;
        // 一个长度为 m 的队列 q，其中队首元素为最早加入的元素，队尾元素为最近加入的元素
        private Deque<Integer> q = new ArrayDeque<Integer>();
        // 三个有序集合，分别为 lo, mid, hi，其中 lo, hi 分别存储最小的 k 个元素和最大的 k 个元素，而 mid 存储剩余的元素
        private TreeMap<Integer, Integer> lo = new TreeMap<Integer, Integer>();
        private TreeMap<Integer, Integer> mid = new TreeMap<Integer, Integer>();
        private TreeMap<Integer, Integer> hi = new TreeMap<Integer, Integer>();

        public MKAverage(int m, int k) {
            this.m = m;
            this.k = k;
        }

        public void addElement(int num) {
            if (lo.isEmpty() || num <= lo.lastKey()) {
                lo.merge(num, 1, Integer::sum);
                ++size1;
            } else if (hi.isEmpty() || num >= hi.firstKey()) {
                hi.merge(num, 1, Integer::sum);
                ++size3;
            } else {
                mid.merge(num, 1, Integer::sum);
                s += num;
            }

            q.offer(num);

            if (q.size() > m) {
                int x = q.poll();
                if (lo.containsKey(x)) {
                    if (lo.merge(x, -1, Integer::sum) == 0)
                        lo.remove(x);
                    --size1;
                } else if (hi.containsKey(x)) {
                    if (hi.merge(x, -1, Integer::sum) == 0)
                        hi.remove(x);
                    --size3;
                } else {
                    if (mid.merge(x, -1, Integer::sum) == 0)
                        mid.remove(x);
                    s -= x;
                }
            }

            for (; size1 > k; --size1) {

                int x = lo.lastKey();

                if (lo.merge(x, -1, Integer::sum) == 0)
                    lo.remove(x);

                mid.merge(x, 1, Integer::sum);
                s += x;
            }

            for (; size3 > k; --size3) {

                int x = hi.firstKey();

                if (hi.merge(x, -1, Integer::sum) == 0)
                    hi.remove(x);

                mid.merge(x, 1, Integer::sum);
                s += x;
            }

            for (; size1 < k && !mid.isEmpty(); ++size1) {

                int x = mid.firstKey();

                if (mid.merge(x, -1, Integer::sum) == 0)
                    mid.remove(x);

                s -= x;
                lo.merge(x, 1, Integer::sum);
            }

            for (; size3 < k && !mid.isEmpty(); ++size3) {

                int x = mid.lastKey();

                if (mid.merge(x, -1, Integer::sum) == 0)
                    mid.remove(x);

                s -= x;
                hi.merge(x, 1, Integer::sum);
            }
        }

        public int calculateMKAverage() {
            return q.size() < m ? -1 : (int) (s / (q.size() - k * 2));
        }
    }
}
