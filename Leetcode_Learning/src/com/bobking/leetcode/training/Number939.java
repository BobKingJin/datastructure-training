package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2023-02-18 12:39
 */
public class Number939 {

    // 参考：https://leetcode.cn/problems/minimum-area-rectangle/solution/zui-xiao-mian-ji-ju-xing-by-leetcode/
    public int minAreaRect(int[][] points) {

        // 将这些点按照 x 轴（即列）排序，那么对于同一列的两个点 (x, y1) 和 (x, y2)，找出它作为右边界的最小的矩形
        Map<Integer, List<Integer>> rows = new TreeMap<Integer, List<Integer>>();
        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            rows.computeIfAbsent(x, z -> new ArrayList()).add(y);
        }

        int ans = Integer.MAX_VALUE;
        Map<Integer, Integer> lastx = new HashMap<Integer, Integer>();

        for (int x : rows.keySet()) {
            List<Integer> row = rows.get(x);
            Collections.sort(row);
            for (int i = 0; i < row.size(); ++i)
                for (int j = i + 1; j < row.size(); ++j) {
                    int y1 = row.get(i);
                    int y2 = row.get(j);
                    int code = 40001 * y1 + y2;
                    if (lastx.containsKey(code))
                        ans = Math.min(ans, (x - lastx.get(code)) * (y2 - y1));
                    lastx.put(code, x);
                }
        }

        return ans < Integer.MAX_VALUE ? ans : 0;
    }
}
