package com.bobking.leetcode.training;

import java.util.Deque;
import java.util.LinkedList;

public class Number1190 {

    // 参考：https://leetcode.cn/problems/reverse-substrings-between-each-pair-of-parentheses/solution/fan-zhuan-mei-dui-gua-hao-jian-de-zi-chu-gwpv/
    public String reverseParentheses(String s) {

        // 从左到右遍历该字符串，使用字符串 str 记录当前层所遍历到的小写英文字母。对于当前遍历的字符：
        // 如果是左括号，将 str 插入到栈中，并将 str 置为空，进入下一层
        // 如果是右括号，则说明遍历完了当前层，需要将 str 反转，返回给上一层
        // 具体地，将栈顶字符串弹出，然后将反转后的 str 拼接到栈顶字符串末尾，将结果赋值给 str
        // 如果是小写英文字母，将其加到 str 末尾
        // 注意到仅在遇到右括号时才进行字符串处理，这样可以保证是按照从括号内到外的顺序处理字符串

        Deque<String> stack = new LinkedList<String>();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(sb.toString());
                // 置空
                sb.setLength(0);
            } else if (ch == ')') {
                sb.reverse();
                sb.insert(0, stack.pop());
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
