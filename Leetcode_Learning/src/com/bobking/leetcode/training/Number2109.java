package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-06-25 10:00
 */
public class Number2109 {

    public String addSpaces(String s, int[] spaces) {

        StringBuilder stringBuilder = new StringBuilder();
        int start = 0;

        for (int space : spaces) {
            stringBuilder.append(s.substring(start, space));
            stringBuilder.append(" ");
            start = space;
        }

        // 还要新增最后一个空格后的字符串
        stringBuilder.append(s.substring(start));
        return stringBuilder.toString();
    }
}
