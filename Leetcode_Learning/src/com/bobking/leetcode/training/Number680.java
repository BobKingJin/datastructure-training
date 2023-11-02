package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-11-02 7:40
 */
public class Number680 {

    public boolean validPalindrome(String s) {

        int front = 0;
        int end = s.length() - 1;

        while (front < end) {
            if (s.charAt(front) != s.charAt(end))
                return check(s, front + 1, end) || check(s, front, end - 1);
            front++;
            end--;
        }
        return true;
    }

    private boolean check(String s, int front, int end) {
        while (front < end) {
            if (s.charAt(front) != s.charAt(end))
                return false;
            front++;
            end--;
        }
        return true;
    }
}
