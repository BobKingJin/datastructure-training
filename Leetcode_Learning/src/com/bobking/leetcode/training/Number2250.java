package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author BobKing
 * @create 2023-04-09 9:28
 */
public class Number2250 {

    // 参考：https://leetcode.cn/problems/count-number-of-rectangles-containing-each-point/solution/pai-xu-pai-xu-pai-xu-pythonjavacgo-by-en-ou4k/
    public int[] countRectangles(int[][] rectangles, int[][] points) {

        // 按照纵坐标从大到小排序
        Arrays.sort(rectangles, (a, b) -> b[1] - a[1]);

        int n = points.length;
        Integer[] ids = IntStream.range(0, n).boxed().toArray(Integer[]::new);
        // 按照纵坐标从大到小排序
        Arrays.sort(ids, (i, j) -> points[j][1] - points[i][1]);

        int[] ans = new int[n];
        // 然后遍历每个点 (xi, yi)，将所有纵坐标不小于 yi 的矩形的横坐标加入一个有序列表 xs
        ArrayList<Integer> xs = new ArrayList<Integer>();
        int i = 0;

        for (int id : ids) {
            int start = i;
            while (i < rectangles.length && rectangles[i][1] >= points[id][1])
                xs.add(rectangles[i++][0]);
            if (start < i)
                // 只有在 xs 插入了新元素时才排序
                Collections.sort(xs);
            ans[id] = i - lowerBound(xs, points[id][0]);
        }
        return ans;
    }

    private int lowerBound(List<Integer> xs, int x) {
        int left = 0;
        int right = xs.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (xs.get(mid) >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
