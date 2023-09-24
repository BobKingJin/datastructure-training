package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author BobKing
 * @create 2021-03-13 20:44
 */
public class Number435 {

    public int eraseOverlapIntervals(int[][] intervals) {

        if (intervals == null || intervals.length == 0)
            return 0;

        // 将数组的第一个数按照从小到大顺序排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int res = 0;

        int compare = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < compare) {
                res++;
            } else {
                compare = intervals[i][1];
            }
        }

        return res;
    }
}
