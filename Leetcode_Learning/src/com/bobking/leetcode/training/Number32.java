package com.bobking.leetcode.training;

import java.util.Stack;

public class Number32 {

    // 参考：程序猿代码指南P291
    public int longestValidParentheses1(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] ch = s.toCharArray();
        // dp[i] 的含义为 str[0...i] 中必须以 str[i] 结尾的最长的有效括号子串长度
        int[] dp = new int[ch.length];
        int res = 0;
        int pre = 0;
        // dp[0] 必为 0
        for (int i = 1; i < ch.length; i++) {
            if (ch[i] == ')') {
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && ch[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                    res = Math.max(res, dp[i]);
                }
            }
        }
        return res;
    }

    // 错误  输入样例")()())"不通过，输出为 2，预期为 4
    // 通过栈底元素为最后一个没有被匹配的右括号的下标，可以避免上述情况
    public int longestValidParentheses2(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty()) {
                    res = Math.max(res, i - stack.peek() + 1);
                    stack.pop();
                }
            }
        }
        return res;
    }

    // 参考：https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/
    public int longestValidParentheses3(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();
        // 栈底元素为最后一个没有被匹配的右括号的下标
        stack.push(-1);
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                // 如果栈为空，说明当前的右括号为没有被匹配的右括号，将其下标放入栈中来更新
                // 之前提到的「最后一个没有被匹配的右括号的下标」
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    // 因为最开始 pop() 弹出了一个数，因此这里直接 i - stack.peek()，而不 + 1
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }

    // 参考：https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/
    public int longestValidParentheses4(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        int res = 0;
        int left = 0;
        int right = 0;

        // 贪心地考虑了以当前字符下标结尾的有效括号长度
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                res = Math.max(res, 2 * right);
                // 当 right > left 时，即以当前字符结尾不可能构成有效括号
            } else if (right > left) {
                left = 0;
                right = 0;
            }
        }

        left = 0;
        right = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                res = Math.max(res, 2 * left);
            } else if (left > right) {
                left = 0;
                right = 0;
            }
        }

        return res;
    }
}
