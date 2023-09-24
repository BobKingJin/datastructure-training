package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-05 6:34
 */
public class Number1266 {

    // 参考：https://leetcode.cn/problems/minimum-time-visiting-all-points/solution/fang-wen-suo-you-dian-de-zui-xiao-shi-jian-by-le-2/
    public int minTimeToVisitAllPoints(int[][] points) {

        int x0 = points[0][0];
        int x1 = points[0][1];
        int ans = 0;

        for (int i = 1; i < points.length; ++i) {
            int y0 = points[i][0];
            int y1 = points[i][1];
            ans += Math.max(Math.abs(x0 - y0), Math.abs(x1 - y1));
            x0 = y0;
            x1 = y1;
        }
        return ans;
    }
}
