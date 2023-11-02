package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-11-01 8:52
 */
public class LCR093 {

    // 参考: https://leetcode.cn/problems/Q91FMA/solutions/1149830/dong-tai-gui-hua-shuang-zhi-zhen-jiu-shi-n54g/
    public int lenLongestFibSubseq(int[] arr) {

        int n = arr.length;
        int max = 0;
        // dp[j][k] 表示以 j, k 为最后两位数字的斐波那契数列
        // 例如: arr = [1, 2, 3, 4, 5, 6, 7, 8], dp[2][3]表示数列 [1, 2, 3]、dp[3][5]表示数列 [1, 2, 3, 5]、dp[5][8]表示数列 [1, 2, 3, 5, 8]
        // 每个 dp元素 的值, 代表它所定位的数列的长度, 例如上面的 dp[2][3] = 3, dp[3][5] = 4, dp[5][8] = 5
        int[][] dp = new int[n][n];
        for (int i = 2; i < n; i++) {
            int j = 0;
            int k = i - 1;
            while (j < k) {
                if (arr[j] + arr[k] == arr[i]) {
                    if (dp[j][k] == 0) {
                        dp[k][i] = 3;
                    } else {
                        dp[k][i] = Math.max(dp[j][k] + 1, dp[k][i]);
                    }
                    max = Math.max(max, dp[k][i]);
                    j++;
                    k--;
                } else if (arr[j] + arr[k] < arr[i]) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return max;
    }
}
