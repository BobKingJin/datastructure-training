package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @Date: 2025/7/13 10:37
 * @Author: BobKing
 * @Description:
 */
public class Number2410 {

    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int n = players.length;
        int m = trainers.length;
        int j = 0;
        for (int i = 0; i < n; i++) {
            int p = players[i];
            while (j < m && trainers[j] < p) {
                j++;
            }
            // 无法找到匹配的训练师
            if (j == m) {
                return i;
            }
            // 匹配一位训练师
            j++;
        }
        // 所有运动员都有匹配的训练师
        return n;
    }

}
