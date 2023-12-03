package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Date: 2023/12/3 10:23
 * @Author: BobKing
 * @Description:
 */
public class Number452 {

    // 参考: https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/solutions/494515/yong-zui-shao-shu-liang-de-jian-yin-bao-qi-qiu-1-2/
    public int findMinArrowShots(int[][] points) {

        if (points.length == 0)
            return 0;

        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] point1, int[] point2) {
                if (point1[1] > point2[1]) {
                    return 1;
                } else if (point1[1] < point2[1]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        int pos = points[0][1];
        int ans = 1;
        for (int[] balloon: points) {
            if (balloon[0] > pos) {
                pos = balloon[1];
                ++ans;
            }
        }
        return ans;
    }
}
