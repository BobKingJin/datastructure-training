package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-06-13 16:46
 */
public class Number72 {

    // 参考：程序猿代码指南P230
    // 空间压缩同见: 程序猿代码指南P230
    public int minDistance(String word1, String word2) {

        if (word1 == null || word2 == null)
            return 0;

        char[] ch1 = word1.toCharArray();
        char[] ch2 = word2.toCharArray();

        // 因为可以为空串，所以这里的 row 和 column 需要加 1
        int row = ch1.length + 1;
        int column = ch2.length + 1;
        // dp[i][j] 表示 str1[0 ... (i - 1)]编辑成 str2[0 ... (j - 1)] 的最小编辑代价
        int[][] dp = new int[row][column];
        // dp[0][0] = 0 默认

        // 第一列
        for (int i = 1; i < row; i++)
            dp[i][0] = i;
        // 第一行
        for (int j = 1; j < column; j++)
            dp[0][j] = j;

        // 左 -> 右  上 -> 下
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                // 替换操作
                if (ch1[i - 1] == ch2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                // 插入操作
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                // 删除操作
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
            }
        }
        return dp[row - 1][column - 1];
    }
}
