package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2023-04-25 7:54
 */
public class Number1763 {

    // 参考：https://leetcode.cn/problems/longest-nice-substring/solution/gong-shui-san-xie-yi-ti-san-jie-po-su-ji-oflj/
    public String longestNiceSubstring1(String s) {

        int n = s.length();
        String ans = "";

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (j - i + 1 > ans.length() && check1(s.substring(i, j + 1)))
                    ans = s.substring(i, j + 1);
            }
        }

        return ans;
    }

    private boolean check1(String s) {
        Set<Character> set = new HashSet<Character>();
        for (char c : s.toCharArray())
            set.add(c);
        for (char c : s.toCharArray()) {
            char a = Character.toLowerCase(c);
            char b = Character.toUpperCase(c);
            if (!set.contains(a) || !set.contains(b))
                return false;
        }
        return true;
    }

    // 参考：https://leetcode.cn/problems/longest-nice-substring/solution/gong-shui-san-xie-yi-ti-san-jie-po-su-ji-oflj/
    public String longestNiceSubstring2(String s) {

        int n = s.length();
        // 构建二维数组 cnt 来记录子串的词频，cnt[i] 为一个长度为 128 的数组
        // 用于记录字符串 s 中下标范围为 [0, i − 1] 的词频。即 cnt[i + 1][j] 所代表的含义为在子串
        // s[0...i] 中字符 j 的出现次数
        int[][] cnt = new int[n + 1][128];

        for (int i = 1; i <= n; i++) {
            char c = s.charAt(i - 1);
            cnt[i] = cnt[i - 1].clone();
            cnt[i][c - 'A']++;
        }

        int idx = -1;
        int len = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (j - i + 1 <= len)
                    continue;
                int[] a = cnt[i];
                int[] b = cnt[j + 1];
                if (check2(a, b)) {
                    idx = i;
                    len = j - i + 1;
                }
            }
        }
        return idx == -1 ? "" : s.substring(idx, idx + len);
    }
    private boolean check2(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            int low = b[i] - a[i];
            // 'A' = 65、'a' = 97
            int up = b[i + 32] - a[i + 32];
            if (low != 0 && up == 0)
                return false;
            if (low == 0 && up != 0)
                return false;
        }
        return true;
    }

}
