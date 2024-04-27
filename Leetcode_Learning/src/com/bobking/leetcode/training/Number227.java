package com.bobking.leetcode.training;

import java.util.Deque;
import java.util.LinkedList;

public class Number227 {

    // 参考；程序猿代码指南P292
    public int calculate(String s) {

        if (s == null || s.length() == 0)
            return 0;

        return value(s.toCharArray(), 0)[0];
    }

    // 返回当前计算结果和当前遍历到的位置
    private int[] value(char[] ch, int i) {

        Deque<String> deque = new LinkedList<String>();
        int pre = 0;
        int[] bra = null;

        while (i < ch.length && ch[i] != ')') {
            if (ch[i] == ' ') {
                i++;
            } else if (ch[i] >= '0' && ch[i] <= '9') {
                pre = pre * 10 + ch[i++] - '0';
            } else if (ch[i] != '(') {
                addNum(deque, pre);
                deque.addLast(String.valueOf(ch[i++]));
                pre = 0;
            } else {
                bra = value(ch, i + 1);
                pre = bra[0];
                i = bra[1] + 1;
            }
        }
        addNum(deque, pre);
        return new int[]{getNum(deque), i};
    }

    private void addNum(Deque<String> deque, int num) {

        if (!deque.isEmpty()) {
            int cur = 0;
            String top = deque.pollLast();
            if ("+".equals(top) || "-".equals(top)) {
                deque.addLast(top);
            } else {
                cur = Integer.valueOf(deque.pollLast());
                num = "*".equals(top) ? (cur * num) : (cur / num);
            }
        }
        deque.addLast(String.valueOf(num));
    }

    private int getNum(Deque<String> deque) {

        int res = 0;
        int num = 0;
        String cur = null;
        boolean add = true;

        while (!deque.isEmpty()) {
            cur = deque.pollFirst();
            if ("+".equals(cur)) {
                add = true;
            } else if ("-".equals(cur)) {
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }
}
