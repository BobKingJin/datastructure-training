package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-08 21:02
 */
public class Number1328 {

    // 参考：https://leetcode.cn/problems/break-a-palindrome/solution/java-by-gfu/
    public String breakPalindrome(String palindrome) {

        int len = palindrome.length();
        int half = (len - 2) >> 1;

        if (len < 2) {
            return "";
        }

        char[] ch = palindrome.toCharArray();
        for (int i = 0; i <= half; ++i) {
            if (ch[i] > 'a') {
                ch[i] = 'a';
                return String.valueOf(ch);
            }
        }
        ch[len - 1] = 'b';
        return String.valueOf(ch);
    }
}
