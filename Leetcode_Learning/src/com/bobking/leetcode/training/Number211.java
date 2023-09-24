package com.bobking.leetcode.training;

public class Number211 {

    class WordDictionary {

        private class Node {
            Node[] tns = new Node[26];
            boolean isWord;
        }

        Node root = new Node();

        public WordDictionary() {

        }

        public void addWord(String word) {

            Node p = root;

            for (int i = 0; i < word.length(); i++) {

                int u = word.charAt(i) - 'a';

                if (p.tns[u] == null)
                    p.tns[u] = new Node();

                p = p.tns[u];
            }

            p.isWord = true;
        }

        public boolean search(String word) {

            return dfs(word, root, 0);
        }

        private boolean dfs(String s, Node p, int sIdx) {

            int n = s.length();
            if (sIdx == n)
                return p.isWord;

            char c = s.charAt(sIdx);
            if (c == '.') {
                // . 可以匹配任一字符
                for (int j = 0; j < 26; j++){
                    if (p.tns[j] != null && dfs(s, p.tns[j], sIdx + 1))
                        return true;
                }

                return false;
            } else {

                int u = c - 'a';

                if (p.tns[u] == null)
                    return false;

                return dfs(s, p.tns[u], sIdx + 1);
            }
        }
    }
}
