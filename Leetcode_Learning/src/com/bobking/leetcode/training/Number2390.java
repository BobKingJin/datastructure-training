package com.bobking.leetcode.training;

import java.util.Stack;

/**
 * @author BobKing
 * @create 2023-01-11 11:41
 */
public class Number2390 {

    public String removeStars(String s) {

        Stack<Character> stk = new Stack<Character>();
        for (Character c : s.toCharArray()) {
            if (c.equals('*')) {
                stk.pop();
            } else {
                stk.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : stk)
            sb.append(c);

        return sb.toString();
    }
}
