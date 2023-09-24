package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-05 6:21
 */
public class Number2432 {

    public int hardestWorker(int n, int[][] logs) {

        int ans = logs[0][0];
        int maxT = logs[0][1];

        for (int i = 1; i < logs.length; i++) {
            int t = logs[i][1] - logs[i - 1][1];
            if (t > maxT || t == maxT && logs[i][0] < ans) {
                ans = logs[i][0];
                maxT = t;
            }
        }
        return ans;
    }
}
