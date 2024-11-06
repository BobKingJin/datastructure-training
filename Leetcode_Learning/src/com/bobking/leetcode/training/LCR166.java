package com.bobking.leetcode.training;

/**
 * @Date: 2024/11/6 10:32
 * @Author: BobKing
 * @Description:
 */
public class LCR166 {

    public int jewelleryValue(int[][] frame) {

        int m = frame.length;
        int n = frame[0].length;

        for (int i = 1; i < m; i++)
            frame[i][0] += frame[i - 1][0];
        for (int j = 1; j < n; j++)
            frame[0][j] += frame[0][j - 1];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                frame[i][j] += Math.max(frame[i][j - 1], frame[i - 1][j]);
            }
        }
        return frame[m - 1][n - 1];
    }
}
