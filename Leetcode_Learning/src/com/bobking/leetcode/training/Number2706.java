package com.bobking.leetcode.training;

/**
 * @Date: 2023/12/29 8:16
 * @Author: BobKing
 * @Description:
 */
public class Number2706 {

    public int buyChoco(int[] prices, int money) {

        // 最小值 mn1, 次小值 mn2
        Integer mn1 = Integer.MAX_VALUE;
        Integer mn2 = Integer.MAX_VALUE;

        for (Integer i : prices) {
            if (i < mn1) {
                mn2 = mn1;
                mn1 = i;
            } else if (i < mn2) {
                mn2 = i;
            }
        }
        return mn1 + mn2 > money ? money : money - mn1 - mn2;
    }
}
