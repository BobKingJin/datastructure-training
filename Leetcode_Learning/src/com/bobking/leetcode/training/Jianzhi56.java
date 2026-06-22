package com.bobking.leetcode.training;

/**
 * @Date: 2026/6/21 16:44
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi56 {

    public int[] FindNumsAppearOnce(int[] nums) {
        int tmp = 0;
        for (int num : nums) {
            tmp ^= num;
        }
        // tmp != 0, 那么 tmp 对应的二进制数中必有一位为 1, 并且 tmp 是由 异或 运算得来的, 那么 tmp 对应的二进制数中为 1 的位
        // 就是两个只出现 1 次数的不同位
        // 找到那个可以充当分组去进行与运算的数
        int mask = 1;
        while ((tmp & mask) == 0) {
            mask <<= 1;
        }

        int a = 0;
        int b = 0;
        for (int num : nums) {
            if ((num & mask) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        int[] res = new int[2];
        res[0] = a < b ? a : b;
        res[1] = a < b ? b : a;
        return res;
    }

}
