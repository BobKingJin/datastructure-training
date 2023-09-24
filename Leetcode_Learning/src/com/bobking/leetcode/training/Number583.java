package com.bobking.leetcode.training;

public class Number583 {

    // 参考：https://leetcode.cn/problems/delete-operation-for-two-strings/solution/gong-shui-san-xie-cong-liang-chong-xu-li-wqv7/
    public int minDistance(String word1, String word2) {

        // 该问题等价于求解两字符的「最长公共子序列」，若两者长度分别为 n 和 m
        // 而最长公共子序列长度为 max，则 n - max + m - max 即为答案


        char[] cs1 = word1.toCharArray();
        char[] cs2 = word2.toCharArray();
        int n = word1.length();
        int m = word2.length();
        int[][] f = new int[n + 1][m + 1];

        // 假定存在哨兵空格，初始化 f[0][x] 和 f[x][0]
        for (int i = 0; i <= n; i++)
            f[i][0] = 1;
        for (int j = 0; j <= m; j++)
            f[0][j] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                if (cs1[i - 1] == cs2[j - 1])
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + 1);
            }
        }
        // 减去哨兵空格
        int max = f[n][m] - 1;
        return n - max + m - max;
    }
}
