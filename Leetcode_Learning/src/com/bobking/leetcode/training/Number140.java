package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-04-03 21:28
 */
public class Number140 {

    // 参考：https://leetcode-cn.com/problems/word-break-ii/solution/dong-tai-gui-hua-hui-su-qiu-jie-ju-ti-zhi-python-d/
    public List<String> wordBreak(String s, List<String> wordDict) {

        // 为了快速判断一个单词是否在单词集合中，需要将它们加入哈希表
        Set<String> wordSet = new HashSet<String>(wordDict);
        int len = s.length();

        // 第 1 步：动态规划计算是否有解
        // dp[i] 表示「长度」为 i 的前缀子串可以拆分成 wordDict 中的单词
        // 长度包括 0 ，因此状态数组的长度为 len + 1
        boolean[] dp = new boolean[len + 1];
        // 0 这个值需要被后面的状态值参考，如果一个单词正好在 wordDict 中，dp[0] 设置成 true 是合理的
        dp[0] = true;

        for (int right = 1; right <= len; right++) {
            // 如果单词集合中的单词长度都不长，从后向前遍历是更快的
            for (int left = right - 1; left >= 0; left--) {
                if (wordSet.contains(s.substring(left, right)) && dp[left]) {
                    dp[right] = true;
                    break;
                }
            }
        }

        // 第 2 步：回溯算法搜索所有符合条件的解
        List<String> res = new ArrayList<String>();

        if (dp[len]) {
            Deque<String> path = new ArrayDeque<String>();
            dfs(s, len, wordSet, dp, path, res);
            return res;
        }
        return res;
    }

    // s[0 : len) 如果可以拆分成 wordSet 中的单词，把递归求解的结果加入 res 中
    private void dfs(String s, int len, Set<String> wordSet, boolean[] dp, Deque<String> path, List<String> res) {

        if (len == 0) {
            res.add(String.join(" ", path));
            return;
        }

        // 可以拆分的左边界从 len - 1 依次枚举到 0
        for (int i = len - 1; i >= 0; i--) {
            String suffix = s.substring(i, len);
            if (wordSet.contains(suffix) && dp[i]) {
                path.addFirst(suffix);
                dfs(s, i, wordSet, dp, path, res);
                // 回溯
                path.removeFirst();
            }
        }
    }
}
