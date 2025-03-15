package com.bobking.leetcode.training;

/**
 * @Date: 2025/3/15 10:37
 * @Author: BobKing
 * @Description:
 */
public class Number3238 {

    public int winningPlayerCount(int n, int[][] pick) {

        int ans = 0;
        int[][] cnts = new int[n][11];
        boolean[] won = new boolean[n];

        for (int[] p : pick) {
            int x = p[0];
            int y = p[1];
            if (!won[x] && ++cnts[x][y] > x) {
                won[x] = true;
                ans++;
            }
        }
        return ans;
    }

}
