package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-06-13 17:28
 */
public class Number97 {

    // 参考：程序猿代码指南P233
    public boolean isInterleave(String s1, String s2, String s3) {

        if (s1 == null || s2 == null || s3 == null)
            return false;

        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        char[] chaim = s3.toCharArray();

        if (ch1.length + ch2.length != chaim.length)
            return false;

        // dp[i][j] 表示 s3[0...i + j - 1] 能否被 s1[0...i - 1]和 s2[0...j - 1]交错组成
        boolean[][] dp = new boolean[ch1.length + 1][ch2.length + 1];
        // 角标为 0 表示空串
        dp[0][0] = true;

        // 第一列
        for (int i = 1; i <= ch1.length; i++) {
            // boolean类型默认为 false
            if (ch1[i - 1] != chaim[i - 1])
                break;

            dp[i][0] = true;
        }
        // 第一行
        for (int j = 1; j <= ch2.length; j++) {
            if (ch2[j - 1] != chaim[j - 1])
                break;

            dp[0][j] = true;
        }
        // 左 ->右  上->下
        for (int i = 1; i <= ch1.length; i++) {
            for (int j = 1; j <= ch2.length; j++) {
                if ((ch1[i - 1] == chaim[i + j - 1] && dp[i - 1][j]) ||
                        (ch2[j - 1] == chaim[i + j - 1] && dp[i][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[ch1.length][ch2.length];
    }
}
