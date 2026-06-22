package com.bobking.leetcode.training;

/**
 * @Date: 2026/6/21 14:02
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi58 {

    public String LeftRotateString1(String str, int n) {
        if (str == null || str.length() < 1 || n > str.length()) {
            return str;
        }
        return str.substring(n) + str.substring(0, n);
    }

    public String LeftRotateString2(String str, int n) {
        if (str.isEmpty() || str.length() == 0) {
            return "";
        }
        int m = str.length();
        n = n % m;

        char[] s = str.toCharArray();
        reverse(s, 0, m - 1);
        reverse(s, 0, m - n - 1);
        reverse(s, m - n, m - 1);
        return new String(s);
    }

    private void reverse(char[] s, int start, int end) {
        while (start < end) {
            swap(s, start++, end--);
        }
    }

    private void swap(char[] s, int a, int b) {
        char temp = s[a];
        s[a] = s[b];
        s[b] = temp;
    }

    public String LeftRotateString3(String str, int n) {

        if (str.isEmpty() || str.length() == 0) {
            return "";
        }

        int m = str.length();
        n = n % m;
        StringBuilder res = new StringBuilder();

        for (int i = n; i < m; i++) {
            res.append(str.charAt(i));
        }
        for (int i = 0; i < n; i++) {
            res.append(str.charAt(i));
        }
        return res.toString();
    }

}
