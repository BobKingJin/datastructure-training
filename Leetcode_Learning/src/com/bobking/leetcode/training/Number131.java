package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Number131 {

    // 参考：https://leetcode-cn.com/problems/palindrome-partitioning/solution/hui-su-you-hua-jia-liao-dong-tai-gui-hua-by-liweiw/
    public List<List<String>> partition1(String s) {

        int len = s.length();
        List<List<String>> res = new ArrayList<List<String>>();
        if (len == 0)
            return res;

        Deque<String> stack = new ArrayDeque<String>();
        char[] charArray = s.toCharArray();
        dfs(charArray, 0, len, stack, res);
        return res;
    }

    private void dfs(char[] charArray, int index, int len, Deque<String> path, List<List<String>> res) {

        if (index == len) {
            res.add(new ArrayList<String>(path));
            return;
        }

        for (int i = index; i < len; i++) {
            // 因为截取字符串是消耗性能的，因此，采用传子串下标的方式判断一个子串是否是回文子串
            if (!checkPalindrome(charArray, index, i))
                continue;
            path.addLast(new String(charArray, index, i + 1 - index));
            dfs(charArray, i + 1, len, path, res);
            // 回溯
            path.removeLast();
        }
    }

    // 这一步的时间复杂度是 O(N)，优化的解法是，先采用动态规划，把回文子串的结果记录在一个表格里
    private boolean checkPalindrome(char[] charArray, int left, int right) {

        while (left < right) {
            if (charArray[left] != charArray[right])
                return false;
            left++;
            right--;
        }
        return true;
    }

    // 参考：https://leetcode-cn.com/problems/palindrome-partitioning/solution/hui-su-you-hua-jia-liao-dong-tai-gui-hua-by-liweiw/
    public List<List<String>> partition2(String s) {

        int len = s.length();
        List<List<String>> res = new ArrayList<List<String>>();
        if (len == 0)
            return res;

        char[] charArray = s.toCharArray();
        // 预处理
        // 状态：dp[i][j] 表示 s[i][j] 是否是回文
        boolean[][] dp = new boolean[len][len];
        // 状态转移方程：在 s[i] == s[j] 的时候，dp[i][j] 参考 dp[i + 1][j - 1]
        // 判断 left - right 范围内是否是回文字符串
        for (int right = 0; right < len; right++) {
            // 注意：left <= right 取等号表示 1 个字符的时候也需要判断
            for (int left = 0; left <= right; left++) {
                if (charArray[left] == charArray[right] && (right - left <= 2 || dp[left + 1][right - 1]))
                    dp[left][right] = true;
            }
        }

        Deque<String> stack = new ArrayDeque<String>();
        dfs(s, 0, len, dp, stack, res);
        return res;
    }

    private void dfs(String s, int index, int len, boolean[][] dp, Deque<String> path, List<List<String>> res) {

        if (index == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < len; i++) {
            if (dp[index][i]) {
                path.addLast(s.substring(index, i + 1));
                dfs(s, i + 1, len, dp, path, res);
                // 回溯
                path.removeLast();
            }
        }
    }
}
