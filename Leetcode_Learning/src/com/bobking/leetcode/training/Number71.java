package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author BobKing
 * @create 2023-09-24 8:54
 */
public class Number71 {

    public String simplifyPath(String path) {

        // item 为有效值：存入栈中
        // item 为 .. ：弹出栈顶元素（若存在）
        // item 为 . ：不作处理
        Deque<String> d = new ArrayDeque<String>();
        int n = path.length();

        for (int i = 1; i < n; ) {
            if (path.charAt(i) == '/') {
                i++;
                continue;
            }
            int j = i + 1;
            while (j < n && path.charAt(j) != '/')
                j++;
            String item = path.substring(i, j);
            if (item.equals("..")) {
                if (!d.isEmpty())
                    d.pollLast();
            } else if (!item.equals(".")) {
                d.addLast(item);
            }
            i = j;
        }
        StringBuilder sb = new StringBuilder();
        while (!d.isEmpty())
            sb.append("/" + d.pollFirst());
        return sb.length() == 0 ? "/" : sb.toString();
    }
}
