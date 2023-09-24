package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-02-26 10:50
 */
public class Number1255 {

    private String[] words;
    private int[] score = new int[26];
    private int[] left = new int[26];
    private int ans;

    // 参考：https://leetcode.cn/problems/maximum-score-words-formed-by-letters/solution/hui-su-san-wen-si-kao-hui-su-wen-ti-de-t-kw3y/
    public int maxScoreWords1(String[] words, char[] letters, int[] score) {

        this.words = words;
        this.score = score;

        for (char c : letters)
            ++left[c - 'a'];

        dfs(words.length - 1, 0);

        return ans;
    }

    private void dfs(int i, int total) {

        if (i < 0) {
            ans = Math.max(ans, total);
            return;
        }

        // 不选 words[i]
        dfs(i - 1, total);

        // 选 words[i]
        char[] s = words[i].toCharArray();
        boolean ok = true;
        for (char c : s) {
            if (left[c - 'a']-- == 0)
                ok = false;
            total += score[c - 'a'];
        }

        if (ok)
            dfs(i - 1, total);

        // 回溯
        for (char c : s)
            ++left[c - 'a'];
    }

    // 参考：https://leetcode.cn/problems/maximum-score-words-formed-by-letters/solution/de-fen-zui-gao-de-dan-ci-ji-he-by-leetco-rwyz/
    public int maxScoreWords2(String[] words, char[] letters, int[] score) {

        int n = words.length;
        int res = 0;
        int[] count = new int[26];

        for (char c : letters)
            count[c - 'a']++;

        for (int s = 1; s < (1 << n); s++) {

            // 统计子集 s 所有单词的字母数目
            int[] wordCount = new int[26];

            for (int k = 0; k < n; k++) {
                // words[k] 不在子集 s 中
                if ((s & (1 << k)) == 0)
                    continue;

                for (int i = 0; i < words[k].length(); i++) {
                    char c = words[k].charAt(i);
                    wordCount[c - 'a']++;
                }
            }
            // 判断子集 s 是否合法
            boolean ok = true;
            // 保存子集 s 的得分
            int sum = 0;
            for (int i = 0; i < 26; i++) {
                sum += score[i] * wordCount[i];
                ok = ok && (wordCount[i] <= count[i]);
            }

            if (ok)
                res = Math.max(res, sum);
        }
        return res;
    }
}
