package com.bobking.leetcode.training;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2023-04-18 7:37
 */
public class Number1696 {

    // 参考：https://leetcode.cn/problems/jump-game-vi/solution/tiao-yue-you-xi-vi-by-zerotrac2-r1kq/
    public int maxResult(int[] nums, int k) {

        int len = nums.length;
        Queue<int[]> queue = new PriorityQueue<int[]>((o1, o2) -> o2[0] - o1[0]);
        queue.offer(new int[]{nums[0], 0});
        int res = nums[0];

        for (int i = 1; i < len; i++) {
            while (i - queue.peek()[1] > k)
                queue.poll();

            res = queue.peek()[0] + nums[i];
            queue.offer(new int[]{res, i});
        }
        return res;
    }
}
