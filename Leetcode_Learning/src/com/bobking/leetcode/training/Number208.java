package com.bobking.leetcode.training;

public class Number208 {

    // 参考：程序猿代码指南P320
    private class Trie {

        private class TrieNode {

            public int path;
            public int end;
            public TrieNode[] map;

            public TrieNode() {
                this.path = 0;
                this.end = 0;
                this.map = new TrieNode[26];
            }
        }

        private TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            this.root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {

            if (word == null) {
                return;
            }

            char[] ch = word.toCharArray();
            TrieNode node = root;
            node.path++;
            int index = 0;

            for (int i = 0; i < ch.length; i++) {
                index = ch[i] - 'a';
                if (node.map[index] == null) {
                    node.map[index] = new TrieNode();
                }
                node = node.map[index];
                node.path++;
            }
            node.end++;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {

            if (word == null) {
                return false;
            }

            char[] ch = word.toCharArray();
            TrieNode node = root;
            int index = 0;

            for (int i = 0; i < ch.length; i++) {
                index = ch[i] - 'a';
                if (node.map[index] == null) {
                    return false;
                }
                node = node.map[index];
            }
            return node.end != 0;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {

            if (prefix == null) {
                return false;
            }

            char[] ch = prefix.toCharArray();
            TrieNode node = root;
            int index = 0;

            for (int i = 0; i < ch.length; i++) {
                index = ch[i] - 'a';
                if (node.map[index] == null) {
                    return false;
                }
                node = node.map[index];
            }
            return node.path != 0;
        }
    }
}
