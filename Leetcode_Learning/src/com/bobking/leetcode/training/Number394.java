package com.bobking.leetcode.training;

import java.util.Stack;

public class Number394 {

    // 参考：https://leetcode-cn.com/problems/decode-string/solution/decode-string-fu-zhu-zhan-fa-di-gui-fa-by-jyd/
    public String decodeString1(String s) {

        // 因为顺序是由内到外进行拼接的，所以应该使用栈
        if (s == null || "".equals(s))
            return s;

        // 用于存放 s 中的数字
        // 用于存放上一个 [ 和当前 [ 之间的数字
        Stack<Integer> numStack = new Stack<Integer>();
        // 用于存放上一个 [ 和当前 [ 之间的字符串，并且可以将当前的结果也压入栈中
        Stack<String> strStack = new Stack<String>();

        int multi = 0;
        StringBuilder res = new StringBuilder();

        for (Character c : s.toCharArray()) {
            if (c.equals('[')) {
                numStack.push(multi);
                strStack.push(res.toString());
                multi = 0;
                res = new StringBuilder();
            } else if (c.equals(']')) {
                StringBuilder temp = new StringBuilder();
                // 把上一个 [ 和当前 [ 之间的数字弹出
                int loop = numStack.pop();
                for (int i = 0; i < loop; i++)
                    temp.append(res);
                // 注意：这个位置是需要 strStack.pop()
                res = new StringBuilder(strStack.pop() + temp.toString());
            } else if (c >= '0' && c <= '9') {
                multi = multi * 10 + Integer.parseInt(c + "");
            } else {
                res.append(c);
            }
        }

        return res.toString();
    }

    // 参考：https://leetcode-cn.com/problems/decode-string/solution/decode-string-fu-zhu-zhan-fa-di-gui-fa-by-jyd/
    public String decodeString2(String s) {

        if (s == null || "".equals(s))
            return s;

        return dfs(s, 0)[0];
    }

    private String[] dfs(String s, int i) {

        StringBuilder res = new StringBuilder();
        int multi = 0;
        while (i < s.length()) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
                multi = multi * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
                // 遇到 [ 即进行递归
            else if (s.charAt(i) == '[') {
                String[] tmp = dfs(s, i + 1);
                i = Integer.parseInt(tmp[0]);
                while (multi > 0) {
                    res.append(tmp[1]);
                    multi--;
                }
            } else if (s.charAt(i) == ']') {
                // 返回 i 的位置和到目前所得到的结果 res
                return new String[]{String.valueOf(i), res.toString()};
            } else {
                res.append(s.charAt(i));
            }
            i++;
        }
        return new String[]{res.toString()};
    }
}


