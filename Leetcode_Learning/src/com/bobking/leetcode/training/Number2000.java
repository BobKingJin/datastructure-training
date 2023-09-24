package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-29 23:31
 */
public class Number2000 {

    public String reversePrefix(String word, char ch) {

        char[] cs = word.toCharArray();
        int n = cs.length;
        int idx = -1;

        for (int i = 0; i < n && idx == -1; i++) {
            if (cs[i] == ch)
                idx = i;
        }

        int l = 0;
        int r = idx;
        // 反转
        while (l < r) {
            char c = cs[l];
            cs[l++] = cs[r];
            cs[r--] = c;
        }
        return String.valueOf(cs);
    }

}
