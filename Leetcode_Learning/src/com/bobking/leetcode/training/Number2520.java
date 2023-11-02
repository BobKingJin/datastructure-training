package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-26 7:41
 */
public class Number2520 {

    public int countDigits(int num) {

        if (num > 0 && num < 10)
            return 1;

        int count = 0;
        int res = num;

        while (num != 0) {
            int d = num % 10;
            if (res % d == 0)
                count++;
            num /= 10;
        }

        return count;
    }
}
