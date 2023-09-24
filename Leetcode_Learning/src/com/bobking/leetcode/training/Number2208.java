package com.bobking.leetcode.training;

import java.util.PriorityQueue;

/**
 * @author BobKing
 * @create 2023-04-26 21:58
 */
public class Number2208 {

    public int halveArray(int[] nums) {

        long sum = 0;
        double res = 0;
        double target = 0;
        int count = 0;
        PriorityQueue<Double> pq = new PriorityQueue<Double>((o1, o2) -> Double.compare(o2, o1));

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            pq.add(nums[i] * 1.0);
        }

        target = sum * 1.0 / 2;

        while (res < target) {
            double temp = pq.poll();
            res += temp / 2;
            pq.add(temp / 2);
            count++;
        }
        return count;
    }
}
