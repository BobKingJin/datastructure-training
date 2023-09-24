package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2021-08-19 21:11
 */
public class Number1278 {

    // 参考：https://leetcode-cn.com/problems/palindrome-partitioning-iii/solution/shu-ju-jie-gou-he-suan-fa-dong-tai-gui-h-rzge/
    public int palindromePartition1(String s, int k) {

        int length = s.length();
        // dp[i][j] 表示 s 的前 i 个字符分割成 j 个子串所修改的最少字符数
        int[][] dp = new int[length + 1][k + 1];
        // 因为这题要求的是所需要修改的最少字符数，初始值赋值尽可能大
        for (int i = 0; i < dp.length; i++)
            Arrays.fill(dp[i], length);

        // 前 i 个字符，分割成 j 个回文子串
        for (int i = 1; i <= length; i++) {
            // 前 i 个字符最大只能分割成 i 个子串，所以不能超过 i
            // 取 i 和 k 的最小值
            int len = Math.min(i, k);
            for (int j = 1; j <= len; j++) {
                if (j == 1) {
                    // 如果 j 等于 1，则表示没有分割，直接计算
                    dp[i][j] = change(s, j - 1, i - 1);
                } else {
                    // 如果 j 不等于 1，计算分割所需要修改的最小字符数，因为 m 的值要大于等于 j - 1
                    // 就从最小的开始枚举
                    for (int m = j - 1; m < i; m++)
                        // 递推公式
                        dp[i][j] = Math.min(dp[i][j], dp[m][j - 1] + change(s, m, i - 1));
                }
            }
        }
        // 返回前 length 个字符分割成 k 个子串所需要修改的最少字符数
        return dp[length][k];
    }

    // 字符串的子串[left, right]变成回文串所需要修改的字符数
    private int change(String s, int left, int right) {

        int count = 0;
        while (left < right) {
            // 如果两个指针指向的字符相同，不需要修改
            // 如果不相同，只需要修改其中的一个即可，所以修改数要加 1
            if (s.charAt(left++) != s.charAt(right--))
                count++;
        }
        return count;
    }

    // 参考：https://leetcode-cn.com/problems/palindrome-partitioning-iii/solution/shu-ju-jie-gou-he-suan-fa-dong-tai-gui-h-rzge/
    public int palindromePartition2(String s, int k) {

        int length = s.length();

        // palindrome[i][j] 表示子串 [i, j] 转化为回文串所需要的修改的字符数
        int[][] palindrome = new int[length][length];
        // 2种实现方式
        //        // 一列一列的从左往右（只遍历右上部分）
        //        for (int j = 1; j < length; j++) {
        //            for (int i = 0; i < j; i++) {
        //                palindrome[i][j] = palindrome[i + 1][j - 1] + (s.charAt(i) == s.charAt(j) ? 0 : 1);
        //            }
        //        }

        // 一行一行的从下往上（只遍历右上部分）
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++)
                palindrome[i][j] = palindrome[i + 1][j - 1] + (s.charAt(i) == s.charAt(j) ? 0 : 1);
        }

        // dp[i][j]表示 s 的前 i 个字符分割成 k 个回文子串的最少次数
        // 第一行和第一列应该都是 0
        int[][] dp = new int[length + 1][k + 1];
        for (int i = 1; i < dp.length; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);

        // 前 i 个字符，分割成 j 个回文子串
        for (int i = 1; i <= length; i++) {
            int len = Math.min(i, k);
            for (int j = 1; j <= len; j++) {
                if (j == 1) {
                    // 字符串的下标是从 0 开始的，所以这里要减 1
                    dp[i][j] = palindrome[j - 1][i - 1];
                } else {
                    for (int m = j - 1; m < i; m++)
                        dp[i][j] = Math.min(dp[i][j], dp[m][j - 1] + palindrome[m][i - 1]);
                }
            }
        }
        return dp[length][k];
    }
}
