package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-04-30 15:34
 */
public class Number242 {

    // 参考：https://leetcode-cn.com/problems/valid-anagram/solution/hua-jie-suan-fa-242-you-xiao-de-zi-mu-yi-wei-ci-by/
    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length())
            return false;

        int[] alpha = new int[26];
        for (int i = 0; i < s.length(); i++) {
            alpha[s.charAt(i) - 'a']++;
            alpha[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++)
            if (alpha[i] != 0)
                return false;

        return true;
    }
}
