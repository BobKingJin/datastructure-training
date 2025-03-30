package com.bobking.leetcode.training;

/**
 * @Date: 2025/3/30 10:11
 * @Author: BobKing
 * @Description:
 */
public class Number2019 {

    public String addSpaces(String s, int[] spaces) {
        StringBuilder ans = new StringBuilder(s.length() + spaces.length);
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (j < spaces.length && spaces[j] == i) {
                ans.append(' ');
                j++;
            }
            ans.append(s.charAt(i));
        }
        return ans.toString();
    }

}
