package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-06-18 20:25
 */
public class Number1451 {

    public String arrangeWords(String text) {

        String[] s = text.toLowerCase().split(" ");

        Arrays.sort(s, (o1, o2) -> {
            return o1.length() - o2.length();
        });

        char first = s[0].charAt(0);
        first = (char) (first - 32);
        String temp = first + s[0].substring(1);
        s[0] = temp;
        String res = "";
        res = String.join(" ", s);

        return res;
    }
}
