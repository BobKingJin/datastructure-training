package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-08 15:58
 */
public class Number1779 {

    public int nearestValidPoint(int x, int y, int[][] points) {

        int ans = -1;
        int mi = 1000000;
        for (int i = 0; i < points.length; ++i) {
            int a = points[i][0];
            int b = points[i][1];
            if (a == x || b == y) {
                int d = Math.abs(a - x) + Math.abs(b - y);
                if (d < mi) {
                    mi = d;
                    ans = i;
                }
            }
        }
        return ans;
    }
}
