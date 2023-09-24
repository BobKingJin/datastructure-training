package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-05-03 20:59
 */
public class Number371 {

    // 参考：https://leetcode-cn.com/problems/sum-of-two-integers/solution/gong-shui-san-xie-shi-yong-wei-yun-suan-4hpb7/
    public int getSum(int a, int b) {

        // 利用二进制的「逢二进一」和「int 二进制表示长度为 32」，可以从低位往高位进行处理，处理过程中使用变量 t 记录进位信息

        int res = 0;
        // 表示进位信息
        int t = 0;
        for (int i = 0; i < 32; i++) {
            int u1 = (a >> i) & 1;
            int u2 = (b >> i) & 1;
            // 两个当前位均为 1：此时当前位是什么取决于前一位的进位情况，即有 res |= (t << i)，同时进位 t = 1
            if (u1 == 1 && u2 == 1) {
                res |= (t << i);
                t = 1;
            } else if (u1 == 1 || u2 == 1) {
                res |= ((1 ^ t) << i);
            } else {
                res |= (t << i);
                t = 0;
            }
        }

        return res;
    }
}
