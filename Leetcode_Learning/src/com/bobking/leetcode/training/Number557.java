package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-05-27 23:26
 */
public class Number557 {

    public String reverseWords(String s) {
        
        StringBuffer res = new StringBuffer();
        int length = s.length();
        int i = 0;

        while (i < length) {

            int start = i;
            while (i < length && s.charAt(i) != ' ')
                i++;

            for (int p = start; p < i; p++)
                res.append(s.charAt(start + i - 1 - p));

            while (i < length && s.charAt(i) == ' ') {
                i++;
                res.append(' ');
            }
        }
        return res.toString();
    }
}
