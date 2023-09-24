package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-10-11 10:10
 */
public class Number1790 {

    // 参考：https://leetcode.cn/problems/check-if-one-string-swap-can-make-strings-equal/solution/by-ac_oier-qeul/
    public boolean areAlmostEqual(String s1, String s2) {

        // 使用 a 和 b 记录不同的位置下标，初始值为 -1，若「不同位置超过 2 个」或「只有 1 个」直接返回 false
        // 若「不存在不同位置」或「不同位置字符相同」，则返回 true

        int n = s1.length();
        int a = -1;
        int b = -1;

        for (int i = 0; i < n; i++) {

            if (s1.charAt(i) == s2.charAt(i))
                continue;

            if (a == -1) {
                a = i;
            } else if (b == -1) {
                b = i;
            } else {
                // 不同位置超过 2 个
                return false;
            }
        }
        // 不存在不同位置
        if (a == b && b == -1)
            return true;

        // 不同位置只有 1 个
        if (a == -1 || b == -1)
            return false;

        return s1.charAt(a) == s2.charAt(b) && s1.charAt(b) == s2.charAt(a);
    }
}
