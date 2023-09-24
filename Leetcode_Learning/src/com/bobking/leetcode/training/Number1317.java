package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-02-18 12:45
 */
public class Number1317 {

    // 参考：https://leetcode.cn/problems/convert-integer-to-the-sum-of-two-no-zero-integers/solution/java0ms-chao-guo-100-fei-bao-li-fa-shi-j-okmq/
    public int[] getNoZeroIntegers(int n) {

        int[] res = new int[2];

        if (n <= 10) {
            res[0] = 1;
            res[1] = n - 1;
            return res;
        }

        // 求数字 n 的十进制长度
        int length = (int) Math.log10(n);

        // 数字 res[0] 中每一位都是 9, res[1] 是与 res[0] 互补的数
        res[0] = (int) Math.pow(10, length) - 1;
        res[1] = n - res[0];

        // 判断 res[1] 中十进制某一位是否为 0
        int temp = res[1];
        int index = 1;

        while (temp > 0) {
            // 如果 res[1] 某一位为 0，则 res[1] 该位加上 1，res[0] 该位减去 1
            if (temp % 10 == 0) {
                res[0] -= index;
                res[1] += index;
            }

            index *= 10;
            temp = temp / 10;
        }
        return res;
    }
}
