package com.bobking.leetcode.training;

import java.util.PriorityQueue;

/**
 * @author BobKing
 * @create 2023-10-18 7:46
 */
public class Number2530 {

    public long maxKelements(int[] nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((o1, o2) -> (o2 - o1));

        for (int num : nums)
            pq.offer(num);

        long sum = 0;
        while (k-- > 0) {
            int max = pq.poll();
            sum += max;
            pq.offer((max + 2) / 3);
        }

        return sum;
    }
}
