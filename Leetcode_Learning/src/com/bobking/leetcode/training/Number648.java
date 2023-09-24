package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author BobKing
 * @create 2022-06-25 11:09
 */
public class Number648 {

    // 参考：https://leetcode.cn/problems/replace-words/solution/dan-ci-ti-huan-by-leetcode/
    public String replaceWords1(List<String> dictionary, String sentence) {

        Set<String> rootSet = new HashSet<String>();
        for (String root : dictionary)
            rootSet.add(root);

        StringBuilder ans = new StringBuilder();
        // \\s+ 为 空格
        for (String word : sentence.split("\\s+")) {
            String prefix = "";
            for (int i = 1; i <= word.length(); ++i) {
                prefix = word.substring(0, i);
                if (rootSet.contains(prefix))
                    break;
            }

            if (ans.length() > 0)
                ans.append(" ");

            ans.append(prefix);
        }

        return ans.toString();
    }

    private class TrieNode {

        TrieNode[] children;
        String word;

        TrieNode() {
            children = new TrieNode[26];
        }
    }

    // 参考：https://leetcode.cn/problems/replace-words/solution/dan-ci-ti-huan-by-leetcode/
    public String replaceWords2(List<String> roots, String sentence) {

        TrieNode trie = new TrieNode();
        // 构建字典树
        for (String root : roots) {
            TrieNode cur = trie;
            for (char letter : root.toCharArray()) {
                if (cur.children[letter - 'a'] == null)
                    cur.children[letter - 'a'] = new TrieNode();
                cur = cur.children[letter - 'a'];
            }
            cur.word = root;
        }

        StringBuilder ans = new StringBuilder();

        for (String word : sentence.split("\\s+")) {

            if (ans.length() > 0)
                ans.append(" ");

            TrieNode cur = trie;
            for (char letter : word.toCharArray()) {
                // cur.word != null 用于判断是否已经结尾了，这个位置在 TrieNode 中没有单独用 end 字段去判断是否是结尾，而是直接用 是否为 null 来判断
                // cur.word == null 则表示每个字符已经遍历完
                if (cur.children[letter - 'a'] == null || cur.word != null)
                    break;
                cur = cur.children[letter - 'a'];
            }

            // 若 cur.word == null，则添加 null 即可
            ans.append(cur.word != null ? cur.word : word);
        }

        return ans.toString();
    }


}
