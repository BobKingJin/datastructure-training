package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-04-30 22:00
 */
public class Number1143 {

    // 参考：程序猿代码指南P220
    public int longestCommonSubsequence1(String text1, String text2) {

        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0)
            return 0;

        char[] ch1 = text1.toCharArray();
        char[] ch2 = text2.toCharArray();
        int[][] dp = new int[ch1.length][ch2.length];

        // dp[i][j] 表示 text1[0...i] 和 text2[0...j] 的最长公共子序列的长度
        dp[0][0] = (ch1[0] == ch2[0]) ? 1 : 0;
        // 第一列
        for (int i = 1; i < ch1.length; i++)
            dp[i][0] = Math.max(dp[i - 1][0], ch1[i] == ch2[0] ? 1 : 0);
        // 第一行
        for (int j = 1; j < ch2.length; j++)
            dp[0][j] = Math.max(dp[0][j - 1], ch1[0] == ch2[j] ? 1 : 0);

        for (int i = 1; i < ch1.length; i++) {
            for (int j = 1; j < ch2.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (ch1[i] == ch2[j])
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }
}
