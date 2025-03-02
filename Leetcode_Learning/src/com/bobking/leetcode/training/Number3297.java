package com.bobking.leetcode.training;

/**
 * @Date: 2025/3/2 12:13
 * @Author: BobKing
 * @Description:
 */
public class Number3297 {

    public long validSubstringCount(String word1, String word2) {

        if (word1.length() < word2.length()) {
            return 0;
        }

        char[] s = word1.toCharArray();
        char[] t = word2.toCharArray();
        // t 的字母出现次数与 s 的字母出现次数之差
        int[] diff = new int[26];
        for (char c : t) {
            diff[c - 'a']++;
        }

        // 统计窗口内有多少个字母的出现次数比 t 的少
        int less = 0;
        for (int d : diff) {
            if (d > 0) {
                less++;
            }
        }

        long ans = 0;
        int left = 0;

        for (char c : s) {
            diff[c - 'a']--;
            if (diff[c - 'a'] == 0) {
                // c 移入窗口后，窗口内 c 的出现次数和 t 的一样
                less--;
            }
            // 窗口符合要求
            while (less == 0) {
                // 准备移出窗口的字母
                char outChar = s[left++];
                if (diff[outChar - 'a'] == 0) {
                    // outChar 移出窗口之前检查出现次数
                    // 如果窗口内 outChar 的出现次数和 t 的一样
                    // 那么 outChar 移出窗口后，窗口内 outChar 的出现次数比 t 的少
                    less++;
                }
                diff[outChar - 'a']++;
            }
            ans += left;
        }
        return ans;
    }

}
