package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2023-06-25 8:00
 */
public class Interview17_13 {

    // 参考：https://leetcode.cn/problems/re-space-lcci/solution/jian-dan-dp-trieshu-bi-xu-miao-dong-by-sweetiee/
    public int respace1(String[] dictionary, String sentence) {

        Set<String> dict = new HashSet<String>(Arrays.asList(dictionary));
        int n = sentence.length();
        // dp[i] 表示字符串的前 i 个字符的最少未匹配数
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int idx = 0; idx < i; idx++) {
                if (dict.contains(sentence.substring(idx, i)))
                    dp[i] = Math.min(dp[i], dp[idx]);
            }
        }
        return dp[n];
    }

    // 参考：https://leetcode.cn/problems/re-space-lcci/solution/jian-dan-dp-trieshu-bi-xu-miao-dong-by-sweetiee/
    public int respace2(String[] dictionary, String sentence) {

        // 构建字典树
        Trie trie = new Trie();
        for (String word: dictionary)
            trie.insert(word);

        int n = sentence.length();
        // 状态转移，dp[i] 表示字符串的前 i 个字符的最少未匹配数
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int idx: trie.search(sentence, i - 1))
                dp[i] = Math.min(dp[i], dp[idx]);
        }
        return dp[n];
    }

}
