package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-12-20 11:18
 */
public class Number2255 {

    public int countPrefixes(String[] words, String s) {

        int count=0;

        for (int i=0; i < words.length ;i++){
            if(s.startsWith(words[i]))
                count++;
        }

        return count;
    }
}
