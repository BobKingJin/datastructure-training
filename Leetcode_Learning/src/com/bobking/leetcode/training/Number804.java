package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2022-04-10 10:28
 */
public class Number804 {

    String[] ss = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    public int uniqueMorseRepresentations(String[] words) {

        if(words == null || words.length < 1)
            return 0;

        Set<String> set = new HashSet<String>();
        for (String s : words) {
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray())
                sb.append(ss[c - 'a']);
            set.add(sb.toString());
        }

        return set.size();
    }
}
