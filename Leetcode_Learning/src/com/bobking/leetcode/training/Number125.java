package com.bobking.leetcode.training;

public class Number125 {

    public boolean isPalindrome(String s) {

        if(s == null || s.length() == 0)
            return true;

        int n = s.length();
        int left = 0;
        int right = n - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left)))
                left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right)))
                right--;
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
                    return false;
                left++;
                right--;
            }
        }
        return true;
    }
}
