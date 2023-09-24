package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author BobKing
 * @create 2021-07-17 11:30
 */
public class Number1288 {

    // 参考：https://leetcode-cn.com/problems/remove-covered-intervals/solution/sao-miao-xian-fa-by-liweiwei1419/
    // 对比程序猿代码指南P214 信封嵌套
    public int removeCoveredIntervals(int[][] intervals) {

        if (intervals == null || intervals.length == 0)
            return 0;

        if (intervals.length < 2)
            return intervals.length;

        // 特别注意：当区间左端点相同的时候，右端点降序排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o2[1] - o1[1];

                return o1[0] - o2[0];
            }
        });

        // 需要被删除的区间个数
        // 因为左边端点已经按序排序了，因此只需比较右边端点
        int remove = 0;
        int currentRight = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][1] <= currentRight) {
                remove++;
            } else {
                currentRight = intervals[i][1];
            }
        }

        return intervals.length - remove;
    }
}
