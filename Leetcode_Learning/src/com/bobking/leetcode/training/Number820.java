package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-08-27 8:12
 */
public class Number820 {

    private class TrieNode {
        char val;
        TrieNode[] children = new TrieNode[26];

        public TrieNode() {}
    }

    private class Trie {

        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public int insert(String word) {

            TrieNode cur = root;
            boolean isNew = false;
            // 倒着插入单词
            for (int i = word.length() - 1; i >= 0; i--) {
                int c = word.charAt(i) - 'a';
                if (cur.children[c] == null) {
                    // 是新单词
                    isNew = true;
                    cur.children[c] = new TrieNode();
                }
                cur = cur.children[c];
            }
            // 如果是新单词的话编码长度增加新单词的长度 + 1，否则不变
            return isNew? word.length() + 1: 0;
        }
    }

    // 参考：https://leetcode.cn/problems/short-encoding-of-words/solution/99-java-trie-tu-xie-gong-lue-bao-jiao-bao-hui-by-s/
    public int minimumLengthEncoding(String[] words) {

        // 只要找到单词列表里，哪些单词被别的单词的后缀给包含了就可以了
        // 只要把单词的倒序插入字典树，再用字典树判断某个单词的逆序是否出现在字典树里就可以了

        int len = 0;
        Trie trie = new Trie();
        // 先对单词列表根据单词长度由长到短排序
        Arrays.sort(words, (s1, s2) -> s2.length() - s1.length());
        // 单词插入trie，返回该单词增加的编码长度
        for (String word: words)
            len += trie.insert(word);

        return len;
    }
}
