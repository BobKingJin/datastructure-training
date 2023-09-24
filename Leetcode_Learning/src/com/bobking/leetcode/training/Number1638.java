package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-28 22:17
 */
public class Number1638 {

    // 参考：https://leetcode.cn/problems/count-substrings-that-differ-by-one-character/solution/python3javacgo-yi-ti-shuang-jie-mei-ju-y-zmin/
    public int countSubstrings1(String s, String t) {

        int ans = 0;
        int m = s.length();
        int n = t.length();

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (s.charAt(i) != t.charAt(j)) {
                    int l = 0;
                    int r = 0;
                    while (i - l > 0 && j - l > 0 && s.charAt(i - l - 1) == t.charAt(j - l - 1))
                        ++l;
                    while (i + r + 1 < m && j + r + 1 < n && s.charAt(i + r + 1) == t.charAt(j + r + 1))
                        ++r;
                    ans += (l + 1) * (r + 1);
                }
            }
        }
        return ans;
    }

    // 参考：https://leetcode.cn/problems/count-substrings-that-differ-by-one-character/solution/python3javacgo-yi-ti-shuang-jie-mei-ju-y-zmin/
    public int countSubstrings2(String s, String t) {

        int ans = 0;
        int m = s.length();
        int n = t.length();

        // 预处理出以每个位置 (i, j) 结尾的最长相同后缀的长度
        // 以及以每个位置 (i, j) 开头的最长相同前缀的长度，分别记录在数组 f 和 g 中
        int[][] f = new int[m + 1][n + 1];
        int[][] g = new int[m + 1][n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (s.charAt(i) == t.charAt(j))
                    f[i + 1][j + 1] = f[i][j] + 1;
            }
        }
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (s.charAt(i) == t.charAt(j)) {
                    g[i][j] = g[i + 1][j + 1] + 1;
                } else {
                    ans += (f[i][j] + 1) * (g[i + 1][j + 1] + 1);
                }
            }
        }
        return ans;
    }

}
