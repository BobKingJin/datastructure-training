package com.bobking.leetcode.training;

import java.util.Stack;

/**
 * @author BobKing
 * @create 2022-09-09 21:37
 */
public class Number1249 {

    // 参考：https://leetcode.cn/problems/minimum-remove-to-make-valid-parentheses/solution/yi-chu-wu-xiao-de-gua-hao-zhan-by-97wgl/
    public String minRemoveToMakeValid(String s) {

        Stack<Integer> bracketIndex = new Stack<Integer>();
        // 用一个 invalidIndex 数组标记无效括号的索引
        boolean[] invalidIndex = new boolean[s.length()];
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                bracketIndex.push(i);
                invalidIndex[i] = true;
            }
            if (s.charAt(i) == ')') {
                if (bracketIndex.empty()) {
                    invalidIndex[i] = true;
                } else {
                    invalidIndex[bracketIndex.pop()] = false;
                }
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (!invalidIndex[i])
                result.append(s.charAt(i));
        }

        return result.toString();
    }

}
