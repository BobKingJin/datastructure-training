package com.bobking.leetcode.training;

public class Number474 {

    // 参考：https://leetcode-cn.com/problems/ones-and-zeroes/solution/dong-tai-gui-hua-zhuan-huan-wei-0-1-bei-bao-wen-ti/
    public int findMaxForm(String[] strs, int m, int n) {

        if (strs == null || strs.length == 0 || m < 0 || n < 0)
            return 0;

        int len = strs.length;
        // dp[i][j][k] 表示输入字符串在子区间 [0, i] 能够使用 j 个 0 和 k 个 1 的字符串的最大数量
        int[][][] dp = new int[len + 1][m + 1][n + 1];

        for (int i = 1; i <= len; i++) {
            // 注意：有一位偏移
            int[] count = countZeroAndOne(strs[i - 1]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {

                    dp[i][j][k] = dp[i - 1][j][k];
                    int zeros = count[0];
                    int ones = count[1];
                    if (j >= zeros && k >= ones)
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                }
            }
        }
        return dp[len][m][n];
    }

    private int[] countZeroAndOne(String str) {

        int[] cnt = new int[2];
        for (char c : str.toCharArray())
            cnt[c - '0']++;

        return cnt;
    }
}
