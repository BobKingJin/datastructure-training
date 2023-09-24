package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-05-01 9:23
 */
public class Number1324 {

    public List<String> printVertically1(String s) {

        List<String> res = new ArrayList<String>();
        String[] split = s.split(" ");
        int col = split[0].length();

        for (String str : split)
            col = Math.max(col, str.length());

        for (int c = 0; c < col; c++) {
            StringBuilder sb = new StringBuilder();
            for (String str : split)
                sb.append(c < str.length() ? str.charAt(c) : " ");
            res.add(trim(sb.toString()));
        }
        return res;
    }

    private String trim(String s) {
        int i = s.length() - 1;
        char[] chars = s.toCharArray();
        while (chars[i] == ' ')
            i--;
        return String.valueOf(chars, 0, i + 1);
    }

    public List<String> printVertically2(String s) {

        List<String> res = new ArrayList<String>();
        String[] split = s.split(" ");
        int col = split[0].length();
        for (String str : split)
            col = Math.max(col, str.length());

        return dfs(split, 0, col);
    }

    private LinkedList<String> dfs(String[] strs, int col, int maxCol) {

        if (col >= maxCol)
            return new LinkedList<String>();

        LinkedList<String> collector = dfs(strs, col + 1, maxCol);
        StringBuilder sb = new StringBuilder();
        for (String str : strs)
            sb.append(col < str.length() ? str.charAt(col) : " ");

        collector.addFirst(trim(sb.toString()));
        return collector;
    }
}
