package com.bobking.leetcode.training;

/**
 * @Date: 2025/3/2 12:30
 * @Author: BobKing
 * @Description:
 */
public class Number1859 {

    public String sortSentence(String s) {

        String[] str = s.split(" ");
        String[] sort = new String[10];

        for (int i = 0; i < str.length; i++) {
            sort[str[i].charAt(str[i].length() - 1) - '0'] = str[i].substring(0,
                str[i].length() - 1);
        }

        String ans = "";
        for (int i = 1; i <= str.length; i++) {
            ans += sort[i];
            if (i != str.length) {
                ans += " ";
            }
        }
        return ans;
    }

}
