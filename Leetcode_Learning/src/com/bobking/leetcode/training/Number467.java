package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-06-01 8:31
 */
public class Number467 {

    int N = 100010;
    int[][] trs = new int[26][N];
    // 定义 f[i] 为以 s[i] 为结尾的最大有效子串的长度
    int[] f = new int[N];
    int[] max = new int[26];
    int n;
    int ans;
    private int lowbit(int x) {
        return x & -x;
    }
    private void add(int[] tr, int x, int v) {
        for (int i = x; i <= n + 1; i += lowbit(i))
            tr[i] += v;
    }
    private int query(int[] tr, int x) {
        int ans = 0;
        for (int i = x; i > 0; i -= lowbit(i))
            ans += tr[i];
        return ans;
    }

    // 参考：https://leetcode.cn/problems/unique-substrings-in-wraparound-string/solution/by-ac_oier-qteu/
    public int findSubstringInWraproundString1(String s) {
        char[] cs = s.toCharArray();
        n = cs.length;
        for (int i = 0; i < n; i++) {
            int c = cs[i] - 'a';
            if (i == 0) {
                f[i] = 1;
            } else {
                int p = cs[i - 1] - 'a';
                if ((c == 0 && p == 25) || p + 1 == c) {
                    f[i] = f[i - 1] + 1;
                } else {
                    f[i] = 1;
                }
            }
            if (max[c] >= f[i])
                continue;
            int cnt = f[i] - query(trs[c], f[i]);
            if (cnt == 0)
                continue;
            ans += cnt;
            add(trs[c], f[i], cnt);
            max[c] = f[i];
        }
        return ans;
    }

    // 参考：https://leetcode.cn/problems/unique-substrings-in-wraparound-string/solutions/1514359/huan-rao-zi-fu-chuan-zhong-wei-yi-de-zi-ndvea/
    public int findSubstringInWraproundString2(String s) {
        // 定义 dp[i] 表示 s 中以字符 s[i] 结尾且在 s 中的子串的最长长度
        int[] dp = new int[26];
        int k = 0;
        for (int i = 0; i < s.length(); ++i) {
            // 字符之差为 1 或 -25
            if (i > 0 && (s.charAt(i) - s.charAt(i - 1) + 26) % 26 == 1) {
                ++k;
            } else {
                k = 1;
            }
            dp[s.charAt(i) - 'a'] = Math.max(dp[s.charAt(i) - 'a'], k);
        }
        return Arrays.stream(dp).sum();
    }
}
