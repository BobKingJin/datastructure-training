package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-01-05 14:43
 */
public class Number2307 {

    // 参考：https://leetcode.cn/problems/minimum-number-of-moves-to-seat-everyone/solution/shi-mei-wei-xue-sheng-du-you-zuo-wei-de-oll4i/
    public int minMovesToSeat(int[] seats, int[] students) {

        Arrays.sort(seats);
        Arrays.sort(students);
        int res = 0;

        for (int i = 0; i < seats.length; i++)
            res += Math.abs(seats[i] - students[i]);

        return res;
    }
}
