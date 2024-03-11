package com.bobking.leetcode.training;

/**
 * @Date: 2024/3/11 0:35
 * @Author: BobKing
 * @Description:
 */
public class Number2129 {

    public String capitalizeTitle(String title) {
        String[] words = title.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].toLowerCase();
            if (words[i].length() > 2) {
                words[i] = (char) (words[i].charAt(0) - 32) + words[i].substring(1);
            }
        }
        return String.join(" ", words);
    }
}
