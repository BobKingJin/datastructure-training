package com.bobking.leetcode.training;

/**
 * @Date: 2025/3/2 11:46
 * @Author: BobKing
 * @Description:
 */
public class Number633 {

    // 参考: https://leetcode.cn/problems/sum-of-square-numbers/solutions/2973811/liang-chong-fang-fa-mei-ju-shuang-zhi-zh-c26z/?envType=daily-question&envId=2025-03-02
    public boolean judgeSquareSum1(int c) {
        for (int a = 0; a * a <= c / 2; a++) {
            int b = (int) Math.sqrt(c - a * a);
            if (a * a + b * b == c) {
                return true;
            }
        }
        return false;
    }

    // 参考: https://leetcode.cn/problems/sum-of-square-numbers/solutions/2973811/liang-chong-fang-fa-mei-ju-shuang-zhi-zh-c26z/?envType=daily-question&envId=2025-03-02
    public boolean judgeSquareSum2(int c) {
        int a = 0;
        int b = (int) Math.sqrt(c);

        while (a <= b) {
            // 避免溢出
            if (a * a == c - b * b) {
                return true;
            }
            if (a * a < c - b * b) {
                a++;
            } else {
                b--;
            }
        }
        return false;
    }

}
