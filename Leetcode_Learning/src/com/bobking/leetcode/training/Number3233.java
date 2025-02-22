package com.bobking.leetcode.training;

/**
 * @Date: 2025/2/16 11:33
 * @Author: BobKing
 * @Description:
 */
public class Number3233 {

    private final int MX = 31622;
    private final int[] PI = new int[MX + 1];

    private void init() {
        for (int i = 2; i <= MX; i++) {
            // i 是质数
            if (PI[i] == 0) {
                PI[i] = PI[i - 1] + 1;
                // 注：如果 MX 比较大，小心 i*i 溢出
                for (int j = i * i; j <= MX; j += i) {
                    // 标记 i 的倍数为合数
                    PI[j] = -1;
                }
            } else {
                PI[i] = PI[i - 1];
            }
        }
    }

    // 参考: https://leetcode.cn/problems/find-the-count-of-numbers-which-are-not-special/solutions/2860276/yu-chu-li-zhi-shu-o1hui-da-pythonjavacgo-7xaq/?envType=daily-question&envId=2025-02-16
    public int nonSpecialCount(int l, int r) {
        init();
        return r - l + 1 - (PI[(int) Math.sqrt(r)] - PI[(int) Math.sqrt(l - 1)]);
    }

}
