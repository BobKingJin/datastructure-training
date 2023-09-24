package com.bobking.leetcode.training;

public class Number516 {

    // 参考：https://leetcode.cn/problems/longest-palindromic-subsequence/solution/zi-xu-lie-wen-ti-tong-yong-si-lu-zui-chang-hui-wen/
    public int longestPalindromeSubseq(String s) {

        // 子串 s[i..j] 中，最长回文子序列的长度为 dp[i][j]
        // 想求 dp[i][j] 需要知道 dp[i + 1][j - 1]，dp[i + 1][j]，dp[i][j - 1] 这三个位置
        // 为了保证每次计算 dp[i][j]，左下右方向的位置已经被计算出来，只能斜着遍历或者反着遍历
        // i 从最后一个字符开始往前遍历，j 从 i + 1 开始往后遍历，这样可以保证每个子问题都已经算好了

        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }
}
