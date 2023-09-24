package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2023-01-31 12:05
 */
public class Number2309 {

    public String greatestLetter1(String s) {

        Set<Character> ss = new HashSet<Character>();
        for (char c : s.toCharArray())
            ss.add(c);

        for (char a = 'Z'; a >= 'A'; --a) {
            if (ss.contains(a) && ss.contains((char) (a + 32)))
                return String.valueOf(a);
        }
        return "";
    }

    public String greatestLetter2(String s) {

        int mask1 = 0;
        int mask2 = 0;

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (Character.isLowerCase(c)) {
                mask1 |= 1 << (c - 'a');
            } else {
                mask2 |= 1 << (c - 'A');
            }
        }

        int mask = mask1 & mask2;
        return mask > 0 ? String.valueOf((char) (31 - Integer.numberOfLeadingZeros(mask) + 'A')) : "";
    }
}
