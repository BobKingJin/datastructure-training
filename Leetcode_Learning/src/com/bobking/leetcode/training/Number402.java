package com.bobking.leetcode.training;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author BobKing
 * @create 2021-05-09 16:56
 */
public class Number402 {

    // 参考：https://leetcode-cn.com/problems/remove-duplicate-letters/solution/yi-zhao-chi-bian-li-kou-si-dao-ti-ma-ma-zai-ye-b-4/
    // 参考：https://leetcode-cn.com/problems/remove-k-digits/solution/wei-tu-jie-dan-diao-zhan-dai-ma-jing-jian-402-yi-d/
    // 单调栈
    public String removeKdigits(String num, int k) {

        if (num == null || num.length() == 0 || "".equals(num) || k < 0 || k > num.length())
            return num;

        char[] ch = num.toCharArray();
        Deque<Character> deque = new LinkedList<Character>();
        for (int i = 0; i < ch.length; i++) {

            char cur = ch[i];
            // 栈顶 -> 栈底 小 -> 大
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > cur) {
                deque.pollLast();
                k--;
            }

            deque.addLast(cur);
        }

        for (int i = 0; i < k; i++)
            deque.pollLast();

        StringBuilder res = new StringBuilder();
        boolean leadingZero = true;
        while (!deque.isEmpty()) {
            char c = deque.pollFirst();
            // 以字符 0 开头
            if (leadingZero && c == '0')
                continue;

            leadingZero = false;
            res.append(c);
        }

        return res.length() == 0 ? "0" : res.toString();
    }
}
