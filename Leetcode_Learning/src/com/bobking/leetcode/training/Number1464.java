package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-08-26 21:25
 */
public class Number1464 {

    public int maxProduct(int[] nums) {

        int a = -1;
        int b = -1;

        for (int x : nums) {
            if (x > a) {
                b = a;
                a = x;
            } else if (x > b) {
                b = x;
            }
        }

        return (a - 1) * (b - 1);
    }
}
