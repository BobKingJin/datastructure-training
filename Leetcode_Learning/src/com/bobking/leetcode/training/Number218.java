package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author BobKing
 * @create 2022-04-30 15:12
 */
public class Number218 {

    public List<List<Integer>> getSkyline(int[][] buildings) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();

        // 预处理所有的点，为了方便排序，对于左端点令高度为负，对于右端点令高度为正
        List<int[]> ps = new ArrayList<int[]>();
        for (int[] b : buildings) {
            int l = b[0];
            int r = b[1];
            int h = b[2];
            ps.add(new int[]{l, -h});
            ps.add(new int[]{r, h});
        }

        // 先按照横坐标进行排序
        // 如果横坐标相同，则按照左端点排序
        // 如果相同的左/右端点，则按照高度进行排序
        Collections.sort(ps, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        // 大根堆
        PriorityQueue<Integer> q = new PriorityQueue<Integer>((a, b) -> b - a);
        int prev = 0;
        q.add(prev);

        for (int[] p : ps) {
            int point = p[0];
            int height = p[1];
            if (height < 0) {
                // 如果是左端点，说明存在一条往右延伸的可记录的边，将高度存入优先队列
                q.add(-height);
            } else {
                // 如果是右端点，说明这条边结束了，将当前高度从队列中移除
                q.remove(height);
            }

            // 取出最高高度，如果当前不与前一矩形"上边"延展而来的那些边重合，则可以被记录
            int cur = q.peek();
            if (cur != prev) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(point);
                list.add(cur);
                res.add(list);
                prev = cur;
            }
        }
        return res;
    }
}
