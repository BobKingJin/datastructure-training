package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-12 8:00
 */
public class Number367 {

    public boolean isPerfectSquare1(int num) {

        long l = 0;
        long r = num;

        while (l < r) {
            long mid = (l + r + 1) >> 1;
            if (mid * mid <= num) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return r * r == num;
    }

    public boolean isPerfectSquare2(int num) {
        int x = 1;
        while (num > 0) {
            num -= x;
            x += 2;
        }
        return num == 0;
    }
}
