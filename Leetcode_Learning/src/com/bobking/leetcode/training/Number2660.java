package com.bobking.leetcode.training;

/**
 * @Date: 2023/12/27 8:02
 * @Author: BobKing
 * @Description:
 */
public class Number2660 {

    public int isWinner(int[] player1, int[] player2) {

        int n = player1.length;
        boolean mark1 = false;
        boolean mark2 = false;
        int count1 = 0;
        int count2 = 0;
        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < n; ++i) {
            if (count1 == 0)  {
                sum1 += player1[i];
            } else {
                sum1 += player1[i] * 2;
                --count1;
            }
            if (count2 == 0) {
                sum2 += player2[i];
            } else {
                sum2 += player2[i] * 2;
                --count2;
            }
            if (player1[i] == 10)
                count1 = 2;
            if (player2[i] == 10)
                count2 = 2;
        }

        if (sum1 == sum2)
            return 0;

        return sum1 - sum2 > 0 ? 1 : 2;
    }
}
