package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-15 9:17
 */
public class Number2399 {

    public boolean checkDistances(String s, int[] distance) {

        int[] last = new int[26];
        char[] chars = s.toCharArray();

        for(int i = 0; i < chars.length; i++){
            if (last[chars[i] - 'a'] > 0 && i - last[chars[i] - 'a'] != distance[chars[i] - 'a'])
                return false;
            last[chars[i] - 'a'] = i + 1;
        }
        return true;
    }
}
