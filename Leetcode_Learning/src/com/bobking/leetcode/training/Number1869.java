package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-09 15:02
 */
public class Number1869 {

    // 参考：https://leetcode.cn/problems/longer-contiguous-segments-of-ones-than-zeros/comments/
    public boolean checkZeroOnes(String s) {

        int curOne = 0;
        int maxOne = 0;
        int curZero = 0;
        int maxZero = 0;

        for (char c : s.toCharArray()) {
            if (c == '1') {
                curOne++;
                curZero = 0;
            }
            if (c == '0') {
                curZero++;
                curOne = 0;
            }

            maxOne = Math.max(maxOne, curOne);
            maxZero = Math.max(maxZero, curZero);
        }

        return maxOne > maxZero;
    }
}
