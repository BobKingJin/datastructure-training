package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Date: 2025/7/19 12:13
 * @Author: BobKing
 * @Description:
 */
public class Number2434 {

    // 参考: https://leetcode.cn/problems/using-a-robot-to-print-the-lexicographically-smallest-string/solutions/1878827/tan-xin-zhan-by-endlesscheng-ldds/?envType=daily-question&envId=2025-07-13
    public String robotWithString(String s) {
        int n = s.length();
        // 计算后缀最小值
        char[] sufMin = new char[n + 1];
        sufMin[n] = Character.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            sufMin[i] = (char) Math.min(sufMin[i + 1], s.charAt(i));
        }

        StringBuilder ans = new StringBuilder(n);
        Deque<Character> st = new ArrayDeque<Character>();
        for (int i = 0; i < n; i++) {
            st.push(s.charAt(i));
            // 如果栈顶 <= 剩余字母(后缀 s[i + 1 : ])中的最小值, 就立刻出栈
            while (!st.isEmpty() && st.peek() <= sufMin[i + 1]) {
                ans.append(st.pop());
            }
        }
        return ans.toString();
    }

}
