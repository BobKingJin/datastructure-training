package com.bobking.leetcode.training;

/**
 * @Date: 2024/11/4 10:34
 * @Author: BobKing
 * @Description:
 */
public class LCR181 {

    public String reverseMessage1(String message) {

        message = message.trim();
        int j = message.length() - 1;
        int i = j;
        StringBuilder res = new StringBuilder();

        while (i >= 0) {
            while (i >= 0 && message.charAt(i) != ' ')
                i--;
            res.append(message.substring(i + 1, j + 1) + " ");
            while (i >= 0 && message.charAt(i) == ' ')
                i--;
            j = i;
        }
        return res.toString().trim();
    }

    public String reverseMessage2(String message) {

        String[] strs = message.trim().split(" ");
        StringBuilder res = new StringBuilder();

        for (int i = strs.length - 1; i >= 0; i--) {
            if (strs[i].equals(""))
                continue;
            res.append(strs[i] + " ");
        }
        return res.toString().trim();
    }

}
