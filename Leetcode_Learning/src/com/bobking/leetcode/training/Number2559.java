package com.bobking.leetcode.training;

import java.util.HashSet;

/**
 * @author BobKing
 * @create 2023-04-12 22:31
 */
public class Number2559 {

    public int[] vowelStrings(String[] words, int[][] queries) {

        String s = "aeiou";
        HashSet<Character> cs = new HashSet<Character>();
        for (char c : s.toCharArray())
            cs.add(c);

        int len = words.length;
        int[] prefixAndArr = new int[len];
        for (int i = 0; i < len; i++) {
            String word = words[i];
            if (isMatch(word, cs)) {
                // 首尾皆是元音
                if (i == 0) {
                    prefixAndArr[i] = 1;
                } else {
                    prefixAndArr[i] += prefixAndArr[i - 1] + 1;
                }
            } else {
                if (i > 0) {
                    // 取上一次值
                    prefixAndArr[i] = prefixAndArr[i - 1];
                }
            }
        }

        len = queries.length;
        int[] rs = new int[len];
        for (int i = 0; i < len; i++) {
            if (queries[i][0] == 0) {
                rs[i] = prefixAndArr[queries[i][1]];
            } else {
                // 求前缀和的差
                // 注意起始索引多减一，相当于求两段的变化：(end - start) + (start - (start - 1))
                rs[i] = prefixAndArr[queries[i][1]] - prefixAndArr[queries[i][0] - 1];
            }
        }
        return rs;
    }

    // 判断首尾是否皆是元音
    private boolean isMatch(String word, HashSet<Character> cs) {
        if (cs.contains(word.charAt(0)) && cs.contains(word.charAt(word.length() - 1))) {
            return true;
        } else {
            return false;
        }
    }
}
