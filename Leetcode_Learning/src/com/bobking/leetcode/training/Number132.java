package com.bobking.leetcode.training;

public class Number132 {

    public int minCut1(String s) {

        // 参考：程序猿代码指南P315
        if (s == null || s.length() == 0)
            return 0;

        char[] ch = s.toCharArray();
        // 从后往前  后 -> 前
        // dp[i] 表示子串 str[i - (len - 1)] 至少需要切割几次才能成为回文子串
        int[] dp = new int[ch.length + 1];
        dp[ch.length] = -1;
        // p[i][j] 表示 str[i] - str[j] 是否是回文子串
        boolean[][] p = new boolean[ch.length][ch.length];
        // 从右往左  前面依赖后面
        for (int i = ch.length - 1; i >= 0; i--) {

            dp[i] = Integer.MAX_VALUE;
            for (int j = i; j <= ch.length - 1; j++) {
                // 因为 i 是从右往左，j 是从左往右 所以在计算 dp[i] 时 p[i + 1][j - 1] 的值一定存在
                if (ch[i] == ch[j] && ((j - i) < 2 || p[i + 1][j - 1])) {

                    dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                    p[i][j] = true;
                }
            }
        }

        return dp[0];
    }

    public int minCut2(String s) {

        if (s == null || s.length() == 0)
            return 0;

        char[] ch = s.toCharArray();
        // 从前往后  前 -> 后
        // dp[i] 表示子串 str[0 - i] 至少需要切割几次才能成为回文子串
        int[] dp = new int[ch.length + 1];
        dp[0] = -1;
        // p[i][j] 表示 str[j] - str[i] 是否是回文子串
        boolean[][] p = new boolean[ch.length][ch.length];
        // 从左往右  后面依赖前面
        for (int i = 1; i <= ch.length - 1; i++) {

            dp[i] = Integer.MAX_VALUE;
            for (int j = i; j >= 0; j--) {
                // 因为 i 是从左往右，j 是从右往左 所以在计算 dp[i] 时 p[i + 1][j - 1] 的值一定存在
                if (ch[i] == ch[j] && ((i - j) < 2 || p[j + 1][i - 1])) {

                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    p[i][j] = true;
                }
            }
        }

        return dp[ch.length];
    }

}
