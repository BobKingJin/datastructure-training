package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-19 11:08
 */
public class Number2108 {

    public String firstPalindrome(String[] words) {

        next:
        for (String word : words) {
            int left = 0;
            int right = word.length() - 1;
            while (left < right) {
                if (word.charAt(left) != word.charAt(right))
                    continue next;

                left++;
                right--;
            }
            return word;
        }
        return "";
    }
}
