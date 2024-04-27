package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Number56 {

    // 未通过
    // [1,4],[0,0] 未通过
    public static int[][] merge1(int[][] intervals) {

        if (intervals == null || intervals.length < 1)
            return null;

        List<int[]> list = new ArrayList<>();

        int index = 1;
        int[] nums = new int[2];
        nums[0] = intervals[0][0];
        nums[1] = intervals[0][1];
        while (index < intervals.length) {
            // 必须先排序，未排序则无法通过
            if (nums[1] >= intervals[index][0]) {
                nums[0] = Math.min(nums[0], intervals[index][0]);
                nums[1] = Math.max(nums[1], intervals[index][1]);
            } else {
                list.add(new int[]{nums[0], nums[1]});
                nums[0] = intervals[index][0];
                nums[1] = intervals[index][1];
            }
            index++;
        }

        list.add(new int[]{nums[0], nums[1]});

        int[][] res = new int[list.size()][2];
        for (int i = 0; i < res.length; i++) {
            int[] tmp = list.get(i);
            res[i][0] = tmp[0];
            res[i][1] = tmp[1];
        }

        return res;
    }

    // 对比程序猿代码指南P214 信封嵌套
    public static int[][] merge2(int[][] intervals) {

        if (intervals == null || intervals.length < 1)
            return null;

        Arrays.sort(intervals, new Comparator<int[]>() {
            // 从小到大排序
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> list = new ArrayList<int[]>();

        int[] nums = new int[2];
        nums[0] = intervals[0][0];
        nums[1] = intervals[0][1];
        int index = 1;

        while (index < intervals.length) {
            if (nums[1] >= intervals[index][0]) {
                nums[1] = Math.max(nums[1], intervals[index][1]);
            } else {
                list.add(new int[]{nums[0], nums[1]});
                nums[0] = intervals[index][0];
                nums[1] = intervals[index][1];
            }
            index++;
        }

        list.add(new int[]{nums[0], nums[1]});
        return list.toArray(new int[list.size()][2]);
    }
}
