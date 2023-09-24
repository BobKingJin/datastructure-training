package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

public class Number729 {

    // 参考：https://leetcode.cn/problems/my-calendar-i/solution/by-ac_oier-1znx/
    private class MyCalendar {

        List<int[]> list = new ArrayList<int[]>();

        public MyCalendar() {

        }

        public boolean book(int start, int end) {

            end--;
            for (int[] info : list) {
                int l = info[0];
                int r = info[1];
                if (start > r || end < l)
                    continue;

                return false;
            }

            list.add(new int[]{start, end});
            return true;
        }
    }
}
