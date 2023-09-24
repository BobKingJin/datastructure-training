package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author BobKing
 * @create 2021-07-17 11:00
 */
public class Number252 {

    public boolean canAttendMeetings(int[][] intervals) {

        if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0)
            return false;

        // 将区间按照会议开始实现升序排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 遍历会议，如果下一个会议在前一个会议结束之前就开始了，返回 false。
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1])
                return false;
        }

        return true;
    }
}
