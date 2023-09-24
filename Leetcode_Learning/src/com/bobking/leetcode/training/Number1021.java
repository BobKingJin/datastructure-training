package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-05-28 14:58
 */
public class Number1021 {

    // 参考：https://leetcode.cn/problems/remove-outermost-parentheses/solution/jian-ji-de-java-shi-xian-by-huaouo/
    public String removeOuterParentheses(String s) {

        StringBuilder sb = new StringBuilder();
        int level = 0;

        for (char c : s.toCharArray()) {
            if (c == ')')
                level--;
            // 去掉最外层
            if (level >= 1)
                sb.append(c);
            if (c == '(')
                level++;
        }

        return sb.toString();
    }
}
