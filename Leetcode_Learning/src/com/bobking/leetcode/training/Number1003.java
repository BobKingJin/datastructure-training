package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-03 8:22
 */
public class Number1003 {

    // 参考：https://leetcode.cn/problems/check-if-word-is-valid-after-substitutions/solution/zhan-jian-ji-xie-fa-pythonjavacgo-by-end-i9o7/
    public boolean isValid(String s) {

        // 同时作为栈
        char[] ch = s.toCharArray();
        // i - 1 表示栈顶下标，i = 0 表示栈为空
        int i = 0;
        for (char c : ch) {
            if (c > 'a' && (i == 0 || c - ch[--i] != 1))
                return false;
            if (c < 'c')
                // 入栈
                ch[i++] = c;
        }
        return i == 0;
    }
}
