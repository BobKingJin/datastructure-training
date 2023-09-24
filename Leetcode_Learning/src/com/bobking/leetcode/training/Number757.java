package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-07-23 14:42
 */
public class Number757 {

    // 参考：https://leetcode.cn/problems/set-intersection-size-at-least-two/solution/by-ac_oier-3xn6/
    public int intersectionSizeTwo(int[][] intervals) {

        Arrays.sort(intervals, (a, b)->{
            return a[1] != b[1] ? a[1] - b[1] : b[0] - a[0];
        });

        int a = -1;
        int b = -1;
        int ans = 0;

        for (int[] i : intervals) {
            if (i[0] > b) {
                a = i[1] - 1;
                b = i[1];
                ans += 2;
            } else if (i[0] > a) {
                a = b;
                b = i[1];
                ans++;
            }
        }

        return ans;
    }
}
