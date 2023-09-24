package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-10 23:13
 */
public class Number1725 {

    public int countGoodRectangles(int[][] rectangles) {

        int max = 0;
        int ans = 0;
        for (int[] r : rectangles) {
            int cur = Math.min(r[0], r[1]);
            if (cur == max) {
                ans++;
            } else if (cur > max) {
                max = cur;
                ans = 1;
            }
        }
        return ans;
    }
}
