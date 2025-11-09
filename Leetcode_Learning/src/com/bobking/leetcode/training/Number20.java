package com.bobking.leetcode.training;

import java.util.Stack;

public class Number20 {

    // 参考：https://leetcode-cn.com/problems/valid-parentheses/solution/valid-parentheses-fu-zhu-zhan-fa-by-jin407891080/
    public boolean isValid(String s) {

        if (s == null || s.length() == 0 || "".equals(s)) {
            return true;
        }

        Stack<Character> stack = new Stack<Character>();
        char[] charArray = s.toCharArray();

        for (char ch : charArray) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                if (!stack.isEmpty()) {
                    if (ch == ')') {
                        if (stack.pop() != '(') {
                            return false;
                        }
                    } else if (ch == '}') {
                        if (stack.pop() != '{') {
                            return false;
                        }
                    } else {
                        if (stack.pop() != '[') {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
