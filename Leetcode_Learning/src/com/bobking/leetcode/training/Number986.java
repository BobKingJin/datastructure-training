package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

public class Number986 {

    // 参考：https://leetcode.cn/problems/interval-list-intersections/solution/jiu-pa-ni-bu-dong-shuang-zhi-zhen-by-hyj8/
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

        List<int[]> res = new ArrayList<int[]>();
        int i = 0;
        int j = 0;

        while (i < firstList.length && j < secondList.length) {
            int left = Math.max(firstList[i][0], secondList[j][0]);
            int right = Math.min(firstList[i][1], secondList[j][1]);
            if (left <= right)
                res.add(new int[] {left, right});
            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return res.toArray(new int[0][0]);
    }
}
