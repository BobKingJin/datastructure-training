package com.bobking.leetcode.training;

import java.util.Deque;
import java.util.LinkedList;

public class Number224 {

    // 参考；程序猿代码指南P292 可以计算包括 + - / *
    public int calculate1(String s) {

        if (s == null || s.length() == 0)
            return 0;

        return value1(s.toCharArray(), 0)[0];
    }

    // 返回当前计算结果和当前遍历到的位置
    private int[] value1(char[] ch, int i) {

        Deque<String> deque = new LinkedList<String>();
        int pre = 0;
        int[] bra = null;

        // 当遇到 i == ch.length 或者 ch[i] == ')' 即递归结束
        while (i < ch.length && ch[i] != ')') {

            if (ch[i] == ' ') {
                i++;
            } else if (ch[i] >= '0' && ch[i] <= '9') {
                pre = pre * 10 + ch[i++] - '0';
            } else if (ch[i] != '(') {
                // 遇到符号 即 + - * /
                addNum1(deque, pre);
                deque.addLast(String.valueOf(ch[i++]));
                pre = 0;
            } else {
                // 遇到 ( 即进行递归
                bra = value1(ch, i + 1);
                pre = bra[0];
                i = bra[1] + 1;
            }
        }

        addNum1(deque, pre);
        return new int[]{getNum1(deque), i};
    }

    private void addNum1(Deque<String> deque, int num) {

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

    private int getNum1(Deque<String> deque) {

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

    // 参考；程序猿代码指南P292 只计算包括 + -
    public int calculate2(String s) {

        if (s == null || s.length() == 0)
            return 0;

        return value2(s.toCharArray(), 0)[0];
    }

    // 返回当前计算结果和当前遍历到的位置
    private int[] value2(char[] ch, int i) {

        Deque<String> deque = new LinkedList<>();
        int pre = 0;
        int[] bra = null;

        while (i < ch.length && ch[i] != ')') {

            if (ch[i] == ' ') {
                i++;
            } else if (ch[i] >= '0' && ch[i] <= '9') {
                pre = pre * 10 + ch[i++] - '0';
            } else if (ch[i] != '(') {
                addNum2(deque, pre);
                deque.addLast(String.valueOf(ch[i++]));
                pre = 0;
            } else {
                bra = value1(ch, i + 1);
                pre = bra[0];
                i = bra[1] + 1;
            }
        }

        addNum2(deque, pre);
        return new int[]{getNum2(deque), i};
    }

    private void addNum2(Deque<String> deque, int num) {

        if (!deque.isEmpty()) {

            String top = deque.pollLast();
            if ("+".equals(top) || "-".equals(top)) {
                deque.addLast(top);
            }
        }
        deque.addLast(String.valueOf(num));
    }

    private int getNum2(Deque<String> deque) {

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

    // 参考：https://leetcode-cn.com/problems/basic-calculator/solution/shuang-zhan-jie-jue-tong-yong-biao-da-sh-olym/
    public int calculate3(String s) {

        if (s == null || s.length() == 0)
            return 0;

        // 存放所有的数字
        Deque<Integer> nums = new LinkedList<>();
        // 为了防止第一个数为负数，先往 nums 加个 0
        nums.addLast(0);
        // 存放所有的操作，包括 +/-
        Deque<Character> ops = new LinkedList<>();

        // 将所有的空格去掉，并将 (- 替换为 (0-，(+ 替换为 (0+
        // 当然这里也可以不预处理，而是放到循环里面去做判断
        s = s.replaceAll(" ", "");
        s = s.replaceAll("\\(-", "(0-");
        s = s.replaceAll("\\(\\+", "(0+");

        char[] ch = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {

            char c = ch[i];
            if (c == '(') {
                ops.addLast(c);
            } else if (c == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    char op = ops.peekLast();
                    if (op != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pollLast();
                        break;
                    }
                }
            } else {
                if (isNum(c)) {
                    int u = 0;
                    int j = i;
                    // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                    while (j < s.length() && isNum(ch[j]))
                        u = u * 10 + ch[j++] - '0';
                    nums.addLast(u);
                    i = j - 1;
                } else {
                    // 有一个新操作要入栈时，先把栈内可以算的都算了
                    while (!ops.isEmpty() && ops.peekLast() != '(')
                        calc(nums, ops);
                    ops.addLast(c);
                }
            }
        }
        while (!ops.isEmpty())
            calc(nums, ops);
        return nums.peekLast();
    }

    private void calc(Deque<Integer> nums, Deque<Character> ops) {

        if (nums.isEmpty() || nums.size() < 2)
            return;
        if (ops.isEmpty())
            return;
        int b = nums.pollLast();
        int a = nums.pollLast();
        char op = ops.pollLast();
        nums.addLast(op == '+' ? a + b : a - b);
    }

    private boolean isNum(char c) {
        return Character.isDigit(c);
    }

}
