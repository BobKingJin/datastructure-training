package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author BobKing
 * @create 2023-05-18 7:54
 */
public class Number2336 {

    class SmallestInfiniteSet1 {
        Set<Integer> set = new HashSet<Integer>();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        // n为非离散区间的起始位置
        int n = 1;

        public SmallestInfiniteSet1() {
        }

        public int popSmallest() {
            if (set.isEmpty())
                return n++;
            int res = pq.poll();
            set.remove(res);
            return res;
        }

        public void addBack(int num) {
            if (num < n && set.add(num))
                pq.add(num);
        }
    }

    class SmallestInfiniteSet2 {
        boolean[] flag = new boolean[1001];
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        // remain 为 n 之前存在的值个数
        int n = 1;
        int remain = 0;
        public SmallestInfiniteSet2() {
        }

        public int popSmallest() {
            if (remain == 0)
                return n++;
            int res = pq.poll();
            flag[res] = false;
            remain--;
            return res;
        }

        public void addBack(int num) {
            if (num < n && !flag[num]) {
                flag[num] = true;
                pq.add(num);
                remain++;
            }
        }
    }
}
