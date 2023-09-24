package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-06-23 21:34
 */
public class Number2212 {

    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {

        int n = 12;
        int maxScore = 0;
        int[] res = new int[n];

        for (int i = 0; i < (1 << n); ++i) {
            // 用 i 来表示每一次结果，i 是一个 12 位的二进制串，如 100000000011 表示第 0、1、11 个区域获胜，其他区域都失败
            int score = 0;
            int usedArrows = 0;
            int[] bobArrows = new int[n];
            for (int j = 0; j < 12; ++j) {
                // 若 i 右移 j 位后再与 1 的结果为 1，即若 i 的第 j 位为 1
                if (((i >> j) & 1) == 1) {
                    score += j;
                    // 使用箭
                    usedArrows += aliceArrows[j] + 1;
                    // 记录箭使用的区域和数量
                    bobArrows[j] = aliceArrows[j] + 1;
                }
            }

            if (usedArrows > numArrows)
                continue;

            if (score > maxScore) {
                maxScore = score;
                // 没用完的箭随意放在第一个区域
                bobArrows[0] += (numArrows - usedArrows);
                res = bobArrows;
            }
        }
        return res;
    }
}
