package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-09-03 11:53
 */
public class Number1006 {

    // 参考：https://leetcode.cn/problems/clumsy-factorial/solution/gong-shui-san-xie-tong-yong-biao-da-shi-nngfp/
    public int clumsy(int n) {

        Deque<Integer> nums = new ArrayDeque<Integer>();
        Deque<Character> ops = new ArrayDeque<Character>();
        // 维护运算符优先级
        Map<Character, Integer> map = new HashMap<Character, Integer>(){{
            put('*', 2);
            put('/', 2);
            put('+', 1);
            put('-', 1);
        }};

        char[] cs = new char[]{'*', '/', '+', '-'};
        for (int i = n, j = 0; i > 0; i--, j++) {
            char op = cs[j % 4];
            nums.addLast(i);
            // 如果「当前运算符优先级」不高于「栈顶运算符优先级」，说明栈内的可以算
            while (!ops.isEmpty() && map.get(ops.peekLast()) >= map.get(op))
                calc(nums, ops);

            if (i != 1)
                ops.add(op);
        }
        // 如果栈内还有元素没有算完，继续算
        while (!ops.isEmpty())
            calc(nums, ops);

        return nums.peekLast();
    }

    private void calc(Deque<Integer> nums, Deque<Character> ops) {

        int b = nums.pollLast();
        int a = nums.pollLast();
        int op = ops.pollLast();
        int ans = 0;

        if (op == '+') {
            ans = a + b;
        } else if (op == '-') {
            ans = a - b;
        } else if (op == '*') {
            ans = a * b;
        } else if (op == '/') {
            ans = a / b;
        }

        nums.addLast(ans);
    }
}
