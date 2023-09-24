package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-29 7:44
 */
public class Number2455 {

    public int averageValue(int[] nums) {

        int sum = 0;
        int cnt = 0;

        for (int x : nums) {
            if (x % 6 == 0) {
                sum += x;
                cnt++;
            }
        }
        return cnt > 0 ? sum / cnt : 0;
    }
}
