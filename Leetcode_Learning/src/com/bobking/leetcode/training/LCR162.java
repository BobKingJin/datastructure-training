package com.bobking.leetcode.training;

/**
 * @Date: 2024/11/15 10:47
 * @Author: BobKing
 * @Description:
 */
public class LCR162 {

    // 参考: https://leetcode.cn/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solutions/135080/javadi-gui-by-xujunyi/
    public int digitOneInNumber(int num) {
        return f(num);
    }

    private int f(int n) {

        if (n <= 0)
            return 0;

        String s = String.valueOf(n);
        int high = s.charAt(0) - '0';
        int pow = (int) Math.pow(10, s.length() - 1);
        int last = n - high * pow;

        if (high == 1) {
            return f(pow - 1) + last + 1 + f(last);
        } else {
            return pow + high * f(pow - 1) + f(last);
        }
    }
}
