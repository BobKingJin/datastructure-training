package com.bobking.leetcode.training;

import java.util.PriorityQueue;

/**
 * @Date: 2025/3/15 11:07
 * @Author: BobKing
 * @Description:
 */
public class Number3066 {

    public int minOperations(int[] nums, int k) {
        int ans = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int x : nums) {
            pq.offer((long) x);
        }

        while (pq.peek() < k) {
            long x = pq.poll();
            long y = pq.poll();
            pq.offer(x * 2 + y);
            ans++;
        }
        return ans;
    }

}
