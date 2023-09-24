package com.bobking.leetcode.training;

import java.util.Stack;

public class Number20 {

    // 参考：https://leetcode-cn.com/problems/valid-parentheses/solution/valid-parentheses-fu-zhu-zhan-fa-by-jin407891080/
    public boolean isValid(String s) {

        if (s == null || s.length() == 0 || "".equals(s))
            return true;

        Stack<Character> stack = new Stack<Character>();
        char[] charArray = s.toCharArray();

        for (char ch : charArray) {
            // 如果是左括号则直接入栈
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                // 如果是右括号，并且此时栈不为空
                if (!stack.isEmpty()) {
                    if (ch == ')') {
                        if (stack.pop() != '(')
                            return false;
                    } else if (ch == '}') {
                        if (stack.pop() != '{')
                            return false;
                    } else {
                        if (stack.pop() != '[')
                            return false;
                    }
                    // 此时栈为空，但却来了个右括号，也直接返回 false
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
