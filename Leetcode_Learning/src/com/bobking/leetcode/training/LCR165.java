package com.bobking.leetcode.training;

/**
 * @Date: 2024/11/6 10:37
 * @Author: BobKing
 * @Description:
 */
public class LCR165 {

    // 参考: https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/solutions/199945/mian-shi-ti-46-ba-shu-zi-fan-yi-cheng-zi-fu-chua-6/
    public int crackNumber(int ciphertext) {

        String s = String.valueOf(ciphertext);
        int a = 1;
        int b = 1;

        for (int i = 2; i <= s.length(); i++) {
            String tmp = s.substring(i - 2, i);
            int c = tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0 ? a + b : a;
            b = a;
            a = c;
        }
        return a;
    }
}
