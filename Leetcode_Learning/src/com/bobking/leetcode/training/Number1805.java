package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

public class Number1805 {

    public int numDifferentIntegers(String word) {

        Set<String> set = new HashSet<String>();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                // 去除前导 0
                while (i < word.length() && word.charAt(i) == '0')
                    i++;

                while (i < word.length() && Character.isDigit(word.charAt(i))) {
                    sb.append(word.charAt(i));
                    i++;
                }
                i -= 1;
                set.add(sb.toString());
            }
        }
        return set.size();
    }
}
