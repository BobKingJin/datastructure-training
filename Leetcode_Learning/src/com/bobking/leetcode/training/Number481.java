package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-10-31 11:59
 */
public class Number481 {

    // 参考：https://leetcode.cn/problems/magical-string/solution/by-endlesscheng-z8o1/
    public int magicalString(int n) {

        char[] s = new char[n + 2];
        s[0] = 1;
        s[1] = 2;
        s[2] = 2;

        char c = 2;

        for (int i = 2, j = 3; j < n; ++i) {
            // 1 ^ 3 = 2, 2 ^ 3 = 1，这样就能在 1 和 2 之间转换
            c ^= 3;
            s[j++] = c;
            if (s[i] == 2)
                s[j++] = c;
        }

        int ans = 0;

        for (int i = 0; i < n; ++i)
            // 2 - 1 = 1，2 - 2 = 0，这样就只统计了 1
            ans += 2 - s[i];

        return ans;
    }
}
