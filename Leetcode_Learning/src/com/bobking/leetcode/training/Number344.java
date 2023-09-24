package com.bobking.leetcode.training;

public class Number344 {

    public void reverseString1(char[] s) {

        if (s == null || s.length == 0)
            return;

        for (int left = 0, right = s.length - 1; left < right; ++left, --right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
    }

    public void reverseString2(char[] s) {

        if (s == null || s.length == 0)
            return;

        swap(0, s.length - 1, s);
    }

    private void swap(int start, int end, char[] s) {

        if (start >= end)
            return;

        char temp = s[start];
        s[start] = s[end];
        s[end] = temp;
        swap(start + 1, end - 1, s);
    }

}
