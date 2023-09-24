package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-10-14 9:51
 */
public class Number940 {

    // 参考：https://leetcode.cn/problems/distinct-subsequences-ii/solution/bu-tong-de-zi-xu-lie-ii-by-leetcode-solu-k2h5/
    public int distinctSubseqII1(String s) {

        final int MOD = 1000000007;
        // 可以用 last[k] 记录第 k (0 ≤ k < 26) 个小写字母最后一次出现的位置
        // 如果它还没有出现过，那么 last[k] = −1
        int[] last = new int[26];
        Arrays.fill(last, -1);

        int n = s.length();
        // 用 f[i] 表示以 s[i] 为最后一个字符的子序列的数目
        int[] f = new int[n];
        Arrays.fill(f, 1);

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 26; ++j) {
                if (last[j] != -1)
                    f[i] = (f[i] + f[last[j]]) % MOD;
            }
            last[s.charAt(i) - 'a'] = i;
        }

        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            if (last[i] != -1)
                ans = (ans + f[last[i]]) % MOD;
        }
        return ans;
    }

    // 参考：https://leetcode.cn/problems/distinct-subsequences-ii/solution/bu-tong-by-capital-worker-vga3/
    public int distinctSubseqII2(String s) {

        int mod = (int) 1e9 + 7;
        int n = s.length();
        // 之前新增的个数
        int[] preCount = new int[26];
        int curAns = 1;
        char[] chs = s.toCharArray();

        for (int i = 0; i < n; i++) {
            // 新增的个数
            int newCount = curAns;
            // 当前序列的个数 = 之前的 + 新增的 - 重复的
            curAns = ((curAns + newCount) % mod - preCount[chs[i] - 'a'] % mod + mod) % mod;
            // 记录当前字符的 新增值
            preCount[chs[i] - 'a'] = newCount;
        }

        // 减去空串
        return curAns - 1;
    }
}
