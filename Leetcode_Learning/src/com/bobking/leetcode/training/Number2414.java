package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-29 15:28
 */
public class Number2414 {

    public int longestContinuousSubstring(String s) {

        // 设 f[i] 是以 s[i] 结尾的最长字母序连续字符串
        int n = s.length();
        int f = 1;
        int res = 1;

        for (int i = 1; i < n; i++) {

            char pre = s.charAt(i - 1);
            int cur = s.charAt(i);
            if (cur == pre + 1) {
                f++;
            } else {
                f = 1;
            }

            if (f > res)
                res = f;
        }

        return res;
    }
}
