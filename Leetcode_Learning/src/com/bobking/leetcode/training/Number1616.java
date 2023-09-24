package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-18 1:06
 */
public class Number1616 {

    // 参考：https://leetcode.cn/problems/split-two-strings-to-make-palindrome/solution/mei-xiang-ming-bai-yi-zhang-tu-miao-dong-imvy/
    public boolean checkPalindromeFormation(String a, String b) {
        return check(a, b) || check(b, a);
    }

    private boolean check(String a, String b) {
        int i = 0;
        int j = a.length() - 1;
        while (i < j && a.charAt(i) == b.charAt(j)) {
            ++i;
            --j;
        }
        return isPalindrome(a, i, j) || isPalindrome(b, i, j);
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j && s.charAt(i) == s.charAt(j)) {
            ++i;
            --j;
        }
        return i >= j;
    }
}
