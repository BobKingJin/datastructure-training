package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @Date: 2025/3/8 22:36
 * @Author: BobKing
 * @Description:
 */
public class Number2545 {

    public int[][] sortTheStudents(int[][] score, int k) {
        Arrays.sort(score, (a, b) -> b[k] - a[k]);
        return score;
    }

}
