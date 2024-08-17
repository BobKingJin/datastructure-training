package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-03-28 14:59
 */
public class Number7 {

    // 参考：https://leetcode-cn.com/problems/reverse-integer/solution/tu-jie-7-zheng-shu-fan-zhuan-by-wang_ni_ma/
    public int reverse(int x) {

        // 不能用 long 存储最终结果，而且有些数字可能是合法范围内的数字，但是反转过来就超过范围了
        // 例如：1147483649 -> 9463847411
        int res = 0;
        // 用取模运算得到整数的 末尾数字 再反转
        // 注意循环判断结束的条件是 x != 0 而不是 x > 0，因为存在 x 为负数的情况
        while (x != 0) {
            // 每次取末尾数字
            int tmp = x % 10;
            // 判断是否 大于 最大 32 位整数
            // 溢出
            if (res > 214748364 || (res == 214748364 && tmp > 7))
                return 0;

            // 判断是否 小于 最小 32 位整数
            if (res < -214748364 || (res == -214748364 && tmp < -8))
                return 0;

            res = res * 10 + tmp;
            x /= 10;
        }

        return res;
    }
}
