package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-10-23 8:26
 */
public class Number1768 {

    public String mergeAlternately(String word1, String word2) {

        int len1 = word1.length();
        int len2 = word2.length();
        int idx = 0;
        char[] res = new char[len1 + len2];

        for(int i = 0; i < len1 || i < len2; ++i) {
            if(i < len1)
                res[idx++] = word1.charAt(i);
            if(i < len2)
                res[idx++] = word2.charAt(i);
        }

        return new String(res);
    }
}
