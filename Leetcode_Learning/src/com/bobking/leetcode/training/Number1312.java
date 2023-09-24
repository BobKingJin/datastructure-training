package com.bobking.leetcode.training;

public class Number1312 {

    // 参考：程序猿代码指南P285
    public int minInsertions(String s) {

        if (s == null || s.length() == 0 || "".equals(s))
            return 0;

        // dp[i][j] 表示子串 s[i ... j] 最少添加几个字符可以使 s[i ... j] 为回文串
        int[][] dp = new int[s.length()][s.length()];
        for (int j = 1; j < s.length(); j++) {
            dp[j - 1][j] = s.charAt(j - 1) == s.charAt(j) ? 0 : 1;
            for (int i = j - 2; i >= 0; i--) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }

        return dp[0][s.length() - 1];
    }
}
