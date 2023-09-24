package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2021-07-17 11:02
 */
public class Number57 {

    // 参考：https://mp.weixin.qq.com/s/ioUlNa4ZToCrun3qb4y4Ow
    // 参考：https://leetcode-cn.com/problems/insert-interval/solution/bi-xu-miao-dong-li-kou-qu-jian-ti-mu-zhong-die-qu-/
    public int[][] insert(int[][] intervals, int[] newInterval) {

        int[][] res = new int[intervals.length + 1][2];
        // 结果集 res 的角标
        int index = 0;

        if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0) {

            res[index++] = newInterval;
            return res;
        }

        if (newInterval == null || newInterval.length == 0)
            return intervals;

        // 首先将新区间左边且相离的区间加入结果集
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0])
            res[index++] = intervals[i++];

        // 接着判断当前区间是否与新区间重叠，重叠的话就进行合并，直到遍历到当前区间在新区间的右边且相离
        // 将最终合并后的新区间加入结果集
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }

        res[index++] = newInterval;
        // 最后将新区间右边且相离的区间加入结果集
        while (i < intervals.length)
            res[index++] = intervals[i++];

        return Arrays.copyOf(res, index);
    }
}
