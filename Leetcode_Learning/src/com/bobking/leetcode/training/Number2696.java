package com.bobking.leetcode.training;

import java.util.ArrayDeque;

/**
 * @Date: 2024/1/10 7:19
 * @Author: BobKing
 * @Description:
 */
public class Number2696 {

    public int minLength1(String s) {

        while (s.contains("AB") || s.contains("CD"))
            s = s.replace("AB", "").replace("CD", "");

        return s.length();
    }

    public int minLength2(String s) {

        ArrayDeque<Character> st = new ArrayDeque<Character>();

        for (var c : s.toCharArray()) {
            if (!st.isEmpty() && (c == 'B' && st.peek() == 'A' || c == 'D' && st.peek() == 'C')) {
                st.pop();
            } else {
                st.push(c);
            }
        }

        return st.size();
    }
}
