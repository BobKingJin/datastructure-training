package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-07-02 9:06
 */
public class Number2490 {
    public boolean isCircularSentence1(String sentence) {

        char[] s = sentence.toCharArray();
        int n = s.length;

        if (s[0] != s[n - 1])
            return false;

        for (int i = 1; i < n - 1; i++) {
            if (s[i] == ' ' && s[i - 1] != s[i + 1])
                return false;
        }

        return true;
    }

    public boolean isCircularSentence2(String sentence) {

        String[] str = sentence.split(" ");
        int len = str.length;

        for (int i = 0; i < str.length; i++) {
            if (str[i].charAt(str[i].length() - 1) != str[(i + 1) % len].charAt(0))
                return false;

        }
        return true;
    }

}
