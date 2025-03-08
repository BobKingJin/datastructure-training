package com.bobking.leetcode.training;

/**
 * @Date: 2025/3/8 22:24
 * @Author: BobKing
 * @Description:
 */
public class Number3270 {

    public int generateKey(int num1, int num2, int num3) {
        int ans = 0;
        for (int pow10 = 1; num1 > 0 && num2 > 0 && num3 > 0; pow10 *= 10) {
            ans += Math.min(Math.min(num1 % 10, num2 % 10), num3 % 10) * pow10;
            num1 /= 10;
            num2 /= 10;
            num3 /= 10;
        }
        return ans;
    }

}
