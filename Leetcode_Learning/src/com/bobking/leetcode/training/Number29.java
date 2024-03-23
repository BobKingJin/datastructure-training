package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-04-03 22:01
 */
public class Number29 {

    // 参考：https://leetcode-cn.com/problems/divide-two-integers/solution/po-su-de-xiang-fa-mei-you-wei-yun-suan-mei-you-yi-/
    public int divide(int dividend, int divisor) {

        // 0 除以任何数等于 0
        if (dividend == 0)
            return 0;

        if (divisor == 1)
            return dividend;

        if (divisor == -1) {
            if (dividend > Integer.MIN_VALUE) {
                return -dividend;
            } else {
                // 溢出
                return Integer.MAX_VALUE;
            }
        }

        boolean positive = true;

        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
            positive = false;
        // 都转为负数
        if (dividend > 0)
            dividend = -dividend;
        if (divisor > 0)
            divisor = -divisor;

        int res = div(dividend, divisor);
        return positive ? res : -res;
    }

    // 例如：60 / 8 = (60 - 32) / 8 + 4 = (60 - 32 - 16) / 8 + 2 + 4 = 1 + 2 + 4 = 7
    private int div(int dividend, int divisor) {

        // 因为都转成了负数，所以当 dividend > divisor 时，结果为 0
        if (dividend > divisor)
            return 0;

        int b = divisor;
        // 当 dividend > divisor 时，结果至少是 1
        int count = 1;
        // dividend，divisor都为负数，所以这里是大于
        // 注意这里条件 b + b < 0，因为溢出之后不再小于 0
        // 必须有 b + b < 0，不然会陷入死循环
        while (b + b >= dividend && b + b < 0) {
            // 结果翻倍
            count = count + count;
            // 除数也翻倍
            b = b + b;
        }
        return count + div(dividend - b, divisor);
    }

}
