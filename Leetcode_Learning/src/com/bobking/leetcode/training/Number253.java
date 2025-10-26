package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Date: 2024/3/1 8:40
 * @Author: BobKing
 * @Description:
 */
public class Number253 {

    public int minMeetingRooms(int[][] intervals) {

        if (intervals.length == 0) {
            return 0;
        }

        PriorityQueue<Integer> allocator = new PriorityQueue<Integer>(intervals.length,
            (a, b) -> a - b);
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        allocator.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= allocator.peek()) {
                allocator.poll();
            }
            allocator.add(intervals[i][1]);
        }
        return allocator.size();
    }
}
