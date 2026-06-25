package com.bobking.leetcode.training;

/**
 * @Date: 2026/6/24 9:30
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi49 {

    public int GetUglyNumber_Solution(int index) {
        if (index <= 6) {
            return index;
        }
        // 丑数的形式就是: 2的x次幂 * 3的y次幂 * 5的z次幂
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;
        int[] res = new int[index];
        // 第一个丑数为 1
        res[0] = 1;

        for (int i = 1; i < index; i++) {
            // 得到下一个丑数，三者中最小的
            res[i] = Math.min(res[i2] * 2, Math.min(res[i3] * 3, res[i5] * 5));
            if (res[i] == res[i2] * 2) {
                i2++;
            }
            if (res[i] == res[i3] * 3) {
                i3++;
            }
            if (res[i] == res[i5] * 5) {
                i5++;
            }
        }
        return res[index - 1];
    }

}
