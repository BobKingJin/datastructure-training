package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-05-28 17:05
 */
public class Number214 {

    // 参考：https://leetcode.cn/problems/shortest-palindrome/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--44/
    public String shortestPalindrome1(String s) {

        int end = s.length() - 1;
        // 找到回文串的结尾, 用 end 标记
        for (; end > 0; end--) {
            if (isPalindromic(s, 0, end))
                break;
        }
        // 将末尾的几个倒置然后加到原字符串开头
        return new StringBuilder(s.substring(end + 1)).reverse() + s;
    }

    private boolean isPalindromic(String s, int start, int end) {

        char[] c = s.toCharArray();
        while (start < end) {
            if (c[start] != c[end])
                return false;
            start++;
            end--;
        }

        return true;
    }

    // 参考：https://leetcode.cn/problems/shortest-palindrome/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--44/
    public String shortestPalindrome2(String s) {

        // 只要 j 进入了最长回文子串，一定会使得 i 走出最长回文子串，那么才可以使用双指针
        int i = 0;
        int j = s.length() - 1;
        char[] c = s.toCharArray();
        while (j >= 0) {

            if (i == j)
                continue;

            if (c[i] == c[j])
                i++;

            j--;
        }
        // 此时代表整个字符串是回文串
        if (i == s.length())
            return s;

        // 后缀
        String suffix = s.substring(i);
        // 后缀倒置
        String reverse = new StringBuilder(suffix).reverse().toString();
        // 加到开头
        return reverse + s;
    }
}
