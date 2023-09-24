package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-04-02 16:22
 */
public class Number66 {

    public int[] plusOne(int[] digits) {

        if(digits == null || digits.length ==0)
            return digits;

        for (int i = digits.length - 1; i >= 0; i--) {

            digits[i]++;
            digits[i] = digits[i] % 10;
            // 没产生进位，直接 digits[digits.length - 1] + 1 返回即可
            if (digits[i] != 0)
                return digits;
        }
        // 如果所有位都是进位，则长度 + 1
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
