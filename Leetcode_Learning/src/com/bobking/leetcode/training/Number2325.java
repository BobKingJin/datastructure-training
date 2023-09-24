package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-02-01 17:39
 */
public class Number2325 {

    public String decodeMessage(String key, String message) {

        char[] d = new char[128];
        d[' '] = ' ';
        for (int i = 0, j = 0; i < key.length(); ++i) {
            char c = key.charAt(i);
            if (d[c] == 0)
                d[c] = (char) ('a' + j++);
        }

        char[] ans = message.toCharArray();
        for (int i = 0; i < ans.length; ++i)
            ans[i] = d[ans[i]];

        return String.valueOf(ans);
    }
}
