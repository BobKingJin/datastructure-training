package com.bobking.leetcode.training;

public class Number221 {

    // 参考：https://leetcode-cn.com/problems/maximal-square/solution/li-jie-san-zhe-qu-zui-xiao-1-by-lzhlyle/
    public int maximalSquare(char[][] matrix) {

        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return 0;
        }

        int height = matrix.length;
        int width = matrix[0].length;
        int maxSide = 0;

        // 相当于已经预处理新增第一行、第一列均为 0, 不需要单独处理 第一行 和 第一列
        int[][] dp = new int[height + 1][width + 1];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (matrix[row][col] == '1') {
                    // 注意这里是取最小值
                    //                          左边              上边               左上
                    dp[row + 1][col + 1] =
                        Math.min(Math.min(dp[row + 1][col], dp[row][col + 1]), dp[row][col]) + 1;
                    maxSide = Math.max(maxSide, dp[row + 1][col + 1]);
                }
            }
        }
        return maxSide * maxSide;
    }
}
