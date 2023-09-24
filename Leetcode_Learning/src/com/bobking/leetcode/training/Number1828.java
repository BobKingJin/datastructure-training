package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-31 12:10
 */
public class Number1828 {

    public int[] countPoints(int[][] points, int[][] queries) {

        int m = queries.length;
        int[] ans = new int[m];

        for (int k = 0; k < m; ++k) {
            int x = queries[k][0];
            int y = queries[k][1];
            int r = queries[k][2];
            for(int n = 0; n < points.length; n++) {
                int i = points[n][0];
                int j = points[n][1];
                int dx = i - x, dy = j - y;
                if (dx * dx + dy * dy <= r * r)
                    ++ans[k];
            }
        }
        return ans;
    }
}
