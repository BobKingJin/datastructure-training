package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author BobKing
 * @create 2022-10-09 10:10
 */
public class Number856 {

    // 参考：https://leetcode.cn/problems/score-of-parentheses/solution/by-ac_oier-0mhz/
    public int scoreOfParentheses(String s) {

        // 初始化将答案 0 放入栈中，从前往后处理整个 s，当遇到 ( 则存入一个占位数值 0，遇到 ) 取出栈顶元素 cur
        // 根据栈顶元素数值值分情况讨论：
        // 栈顶元素 cur = 0，即当前的 ) 的前一元素即是 ( ，根据 () 得一分的规则可知，本次操作得到的分值为 1
        // 栈顶元素 cur != 0，即当前 ) 与其匹配的 ( 中间相隔了其他字符，根据 (A) 的得分规则，此时可知得分为 cur × 2
        // 将两者结合可统一为 max(cur × 2, 1)
        // 由于每次遇到 ) 时，都将最近一次操作计算出来。而再前面无论是 ) 还是 ( 都可以归结到 X() 的相邻项累加规则，将其新得分累加到栈顶元素上
        // 其中 ( 仍采用累加规则，则利用将 ( 定义为 0 的设定

        Deque<Integer> d = new ArrayDeque<Integer>();
        d.addLast(0);

        for (char c : s.toCharArray()) {
            if (c == '(') {
                d.addLast(0);
            } else {
                int cur = d.pollLast();
                d.addLast(d.pollLast() + Math.max(cur * 2 , 1));
            }
        }

        return d.peekLast();
    }
}
