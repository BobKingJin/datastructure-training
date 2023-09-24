package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-05-28 0:04
 */
public class Number58 {

    public int lengthOfLastWord(String s) {

        int end = s.length() - 1;

        while(end >= 0 && s.charAt(end) == ' ')
            end--;

        if(end < 0)
            return 0;

        int start = end;
        while(start >= 0 && s.charAt(start) != ' ')
            start--;

        return end - start;
    }
}
