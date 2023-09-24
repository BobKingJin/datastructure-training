package com.bobking.leetcode.training;

import java.util.HashMap;

/**
 * @author BobKing
 * @create 2023-09-24 9:18
 */
public class Number87 {

    // 参考：https://leetcode.cn/problems/scramble-string/solutions/51990/miao-dong-de-qu-jian-xing-dpsi-lu-by-sha-yu-la-jia/
    public boolean isScramble1(String s1, String s2) {

        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        int n = s1.length();
        int m = s2.length();
        if (n != m)
            return false;

        // dp[i][j][k][h] 表示 T[k..h] 是否由 S[i..j] 变来
        // 由于变换必须长度是一样的，因此这边有个关系 j - i = h - k，可以把四维数组降成三维
        // dp[i][j][len] 表示从字符串 S 中 i 开始长度为 len 的字符串是否能变换为从字符串 T 中 j 开始长度为 len 的字符串
        boolean[][][] dp = new boolean[n][n][n + 1];
        // 初始化单个字符的情况
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                dp[i][j][1] = (chs1[i] == chs2[j]);
        }

        // 枚举区间长度 2 ～ n
        for (int len = 2; len <= n; len++) {
            // 枚举 S 中的起点位置
            for (int i = 0; i <= n - len; i++) {
                // 枚举 T 中的起点位置
                for (int j = 0; j <= n - len; j++) {
                    // 枚举划分位置
                    for (int k = 1; k <= len - 1; k++) {
                        // 第一种情况：S1 -> T1, S2 -> T2
                        if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                        // 第二种情况：S1 -> T2, S2 -> T1
                        // S1 起点 i，T2 起点 j + 前面那段长度 len - k ，S2 起点 i + 前面长度 k
                        if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }

    // 参考：https://leetcode.cn/problems/scramble-string/solutions/51990/miao-dong-de-qu-jian-xing-dpsi-lu-by-sha-yu-la-jia/
    public boolean isScramble2(String s1, String s2) {

        if (s1.length() != s2.length())
            return false;

        if (s1.equals(s2))
            return true;

        int n = s1.length();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < n; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            map.put(c1, map.getOrDefault(c1, 0) + 1);
            map.put(c2, map.getOrDefault(c2, 0) - 1);
        }
        for (Character key : map.keySet()) {
            if (map.get(key) != 0)
                return false;
        }

        // 开始判断，满足一个就能 return true
        for (int i = 1; i < n; i++) {
            boolean flag =
                    // S1 -> T1，S2 -> T2
                    (isScramble2(s1.substring(0, i), s2.substring(0, i)) && isScramble2(s1.substring(i), s2.substring(i))) ||
                            // S1 -> T2，S2 -> T1
                            (isScramble2(s1.substring(0, i), s2.substring(n - i)) && isScramble2(s1.substring(i), s2.substring(0, s2.length() - i)));
            if (flag)
                return true;
        }
        return false;
    }
}
