package com.bobking.leetcode.training;

import java.util.Stack;

public class Number150 {

    // 参考：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/solution/javachun-shu-zu-he-zhan-liang-chong-shi-xian-jian-/
    public static int evalRPN(String[] tokens) {

        Stack<Integer> numStack = new Stack<Integer>();
        Integer op1;
        Integer op2;
        for (String s : tokens) {
            switch (s) {
                case "+":
                    op2 = numStack.pop();
                    op1 = numStack.pop();
                    numStack.push(op1 + op2);
                    break;
                case "-":
                    op2 = numStack.pop();
                    op1 = numStack.pop();
                    numStack.push(op1 - op2);
                    break;
                case "*":
                    op2 = numStack.pop();
                    op1 = numStack.pop();
                    numStack.push(op1 * op2);
                    break;
                case "/":
                    op2 = numStack.pop();
                    op1 = numStack.pop();
                    numStack.push(op1 / op2);
                    break;
                default:
                    numStack.push(Integer.valueOf(s));
                    break;
            }
        }

        return numStack.pop();
    }

}
