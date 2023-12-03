package com.bobking.leetcode.training;

import java.util.Arrays;

public class Number1657 {

    public boolean closeStrings(String word1, String word2) {

        int[] c1 = new int[26];
        int[] c2 = new int[26];
        for (char c : word1.toCharArray())
            c1[c - 'a']++;
        for (char c : word2.toCharArray())
            c2[c - 'a']++;
        for (int i = 0; i < 26; i++) {
            if (c1[i] + c2[i] == 0)
                continue;
            // 若存在某个字符仅在 s1 或 s2 中出现过，两字符串必不接近
            if (c1[i] == 0 || c2[i] == 0)
                return false;
        }
        Arrays.sort(c1);
        Arrays.sort(c2);
        for (int i = 0; i < 26; i++) {
            if (c1[i] != c2[i])
                return false;
        }
        return true;
    }
}
