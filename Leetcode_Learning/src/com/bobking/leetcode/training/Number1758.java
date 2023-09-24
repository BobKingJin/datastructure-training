package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-29 16:34
 */
public class Number1758 {

    // 参考：https://leetcode.cn/problems/minimum-changes-to-make-alternating-binary-string/solution/by-ac_oier-gclh/
    public int minOperations(String s) {

        int n = s.length();
        int cnt = 0;

        for (int i = 0; i < n; i++)
            cnt += (s.charAt(i) - '0') ^ (i & 1);

        return Math.min(cnt, n - cnt);
    }
}
