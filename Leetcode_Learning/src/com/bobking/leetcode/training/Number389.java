package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-06-12 10:12
 */
public class Number389 {

    public char findTheDifference1(String s, String t) {

        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i)
            cnt[s.charAt(i) - 'a']++;

        for (int i = 0; i < t.length(); ++i) {
            char ch = t.charAt(i);
            cnt[ch - 'a']--;
            if (cnt[ch - 'a'] < 0)
                return ch;
        }

        return ' ';
    }

    public char findTheDifference2(String s, String t) {

        int as = 0;
        int at = 0;
        for (int i = 0; i < s.length(); ++i)
            as += s.charAt(i);

        for (int i = 0; i < t.length(); ++i)
            at += t.charAt(i);

        return (char) (at - as);
    }

    // 如果将两个字符串拼接成一个字符串，则问题转换成求字符串中出现奇数次的字符
    public char findTheDifference3(String s, String t) {

        int ret = 0;
        for (int i = 0; i < s.length(); ++i)
            ret ^= s.charAt(i);

        for (int i = 0; i < t.length(); ++i)
            ret ^= t.charAt(i);

        return (char) ret;
    }

}
