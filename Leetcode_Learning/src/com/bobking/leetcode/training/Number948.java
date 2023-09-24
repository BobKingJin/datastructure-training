package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-04-30 8:50
 */
public class Number948 {

    public int bagOfTokensScore(int[] tokens, int power) {

        Arrays.sort(tokens);
        int l = 0;
        int r = tokens.length - 1;
        int points = 0;
        int p = power;

        while (l <= r) {
            if (p >= tokens[l]) {
                // 如果当前的能量可以换积分，那就赶紧换积分；然后开始下一轮兑换
                p -= tokens[l];
                points++;
                l++;
            } else if (points > 0 && l < r) {
                // 如果有得分，且有最大的能量可以兑换，那就兑换能量
                p += tokens[r];
                points--;
                r--;
            } else {
                // 无法换积分也无法换能量就退出
                break;
            }
        }
        return points;
    }
}
