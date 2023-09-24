package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-24 8:30
 */
public class Number522 {

    // 参考：https://leetcode.cn/problems/longest-uncommon-subsequence-ii/solution/by-ac_oier-vuez/
    public int findLUSlength(String[] strs) {

        int n = strs.length;
        int ans = -1;

        for (int i = 0; i < n; i++) {

            if (strs[i].length() <= ans)
                continue;

            boolean ok = true;

            for (int j = 0; j < n && ok; j++) {
                if (i == j)
                    continue;
                if (check(strs[i], strs[j]))
                    ok = false;
            }

            if (ok)
                ans = strs[i].length();
        }

        return ans;
    }

    // check 函数来检查 s1 是否为 s2 的子序列
    // 该问题可转化为求 s1 和 s2 的最长公共子序列长度
    // 若最长公共子序列长度为 s1 长度，说明 s1 为 s2 的子序列
    private boolean check(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        if (m < n)
            return false;

        int[][] f = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                f[i][j] = s1.charAt(i - 1) == s2.charAt(j - 1) ? f[i - 1][j - 1] + 1 : f[i - 1][j - 1];

                f[i][j] = Math.max(f[i][j], f[i - 1][j]);

                f[i][j] = Math.max(f[i][j], f[i][j - 1]);

                if (f[i][j] == n)
                    return true;
            }
        }

        return false;
    }
}
