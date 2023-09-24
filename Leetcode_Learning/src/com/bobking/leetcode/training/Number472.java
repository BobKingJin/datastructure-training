package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2023-01-15 9:28
 */
public class Number472 {

    Set<Long> set = new HashSet<Long>();
    int P = 131;
    int OFFSET = 128;

    // 参考：https://leetcode.cn/problems/concatenated-words/solution/gong-shui-san-xie-xu-lie-dpzi-fu-chuan-h-p7no/
    public List<String> findAllConcatenatedWordsInADict1(String[] words) {

        for (String s : words) {
            long hash = 0;
            for (char c : s.toCharArray())
                hash = hash * P + (c - 'a') + OFFSET;
            set.add(hash);
        }

        List<String> ans = new ArrayList<String>();
        for (String s : words) {
            if (check(s))
                ans.add(s);
        }

        return ans;
    }

    private boolean check(String s) {

        int n = s.length();
        // 定义 f[i] 为考虑 s 的前 i 个字符（令下标从 1 开始），能够切分出的最大 item 数的个数
        int[] f = new int[n + 1];
        Arrays.fill(f, -1);
        f[0] = 0;

        for (int i = 0; i <= n; i++) {
            if (f[i] == -1)
                continue;
            long cur = 0;
            for (int j = i + 1; j <= n; j++) {
                cur = cur * P + (s.charAt(j - 1) - 'a') + OFFSET;
                if (set.contains(cur))
                    f[j] = Math.max(f[j], f[i] + 1);
            }
            if (f[n] > 1)
                return true;
        }
        return false;
    }

    private class Trie {
        Trie[] children;
        boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }
    }

    Trie trie = new Trie();

    // 参考：https://leetcode.cn/problems/concatenated-words/solution/lian-jie-ci-by-leetcode-solution-mj4d/
    public List<String> findAllConcatenatedWordsInADict2(String[] words) {

        List<String> ans = new ArrayList<String>();
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        for (int i = 0; i < words.length; i++) {

            String word = words[i];

            if (word.length() == 0)
                continue;

            boolean[] visited = new boolean[word.length()];

            if (dfs(word, 0, visited)) {
                ans.add(word);
            } else {
                insert(word);
            }
        }
        return ans;
    }

    private boolean dfs(String word, int start, boolean[] visited) {

        if (word.length() == start)
            return true;

        if (visited[start])
            return false;

        visited[start] = true;
        Trie node = trie;

        for (int i = start; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            node = node.children[index];

            if (node == null)
                return false;

            if (node.isEnd) {
                if (dfs(word, i + 1, visited))
                    return true;
            }
        }
        return false;
    }

    private void insert(String word) {
        Trie node = trie;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null)
                node.children[index] = new Trie();
            node = node.children[index];
        }
        node.isEnd = true;
    }

}
