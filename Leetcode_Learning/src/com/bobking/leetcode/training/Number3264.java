package com.bobking.leetcode.training;

import java.util.PriorityQueue;

/**
 * @Date: 2025/3/30 10:34
 * @Author: BobKing
 * @Description:
 */
public class Number3264 {

    public int[] getFinalState(int[] nums, int k, int multiplier) {
        // 小根堆
        PriorityQueue<Integer> pq
            = new PriorityQueue<Integer>(
            (i, j) -> nums[i] - nums[j] == 0 ? i - j : nums[i] - nums[j]);
        for (int i = 0; i < nums.length; i++) {
            pq.offer(i);
        }
        while (k-- > 0) {
            int i = pq.poll();
            nums[i] *= multiplier;
            pq.offer(i);
        }
        return nums;
    }

}
