package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

public class Number745 {

    class WordFilter1 {

        Map<String, Integer> dictionary;

        public WordFilter1(String[] words) {

            dictionary = new HashMap<String, Integer>();
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                int m = word.length();
                // 前缀长度
                for (int prefixLength = 1; prefixLength <= m; prefixLength++) {
                    // 后缀长度
                    for (int suffixLength = 1; suffixLength <= m; suffixLength++)
                        dictionary.put(word.substring(0, prefixLength) + "#" + word.substring(m - suffixLength), i);
                }
            }
        }

        public int f(String pref, String suff) {
            return dictionary.getOrDefault(pref + "#" + suff, -1);
        }
    }

    private class WordFilter2 {

        // 调用 f 时，如果前缀和后缀的长度相同，那么此题可以用字典树来解决
        // 初始化时，只需将单词正序和倒序后得到的单词对依次插入字典树即可
        // 比如要插入 apple 时，只需依次插入 (`a', `e'), (`p', `l'),
        // (`p', `p'), (`l', `p'), (`e', `a')即可
        // 这样初始化后，对于前缀和后缀相同的检索，也只需要在字典树上检索前缀和后缀倒序得到的单词对
        // 但是调用 f 时，还有可能遇到前缀和后缀长度不同的情况。为了应对这一情况，可以将短的字符串用特殊字符补足
        // 使得前缀和后缀长度相同。而在初始化时，也需要考虑到这个情况，特殊字符组成的单词对，也要插入字典树中

        Trie trie;
        String weightKey;

        public WordFilter2(String[] words) {

            trie = new Trie();
            weightKey = "##";
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                Trie cur = trie;
                int m = word.length();
                for (int j = 0; j < m; j++) {
                    Trie tmp = cur;
                    for (int k = j; k < m; k++) {
                        String key = new StringBuilder().append(word.charAt(k)).append('#').toString();
                        if (!tmp.children.containsKey(key))
                            tmp.children.put(key, new Trie());
                        tmp = tmp.children.get(key);
                        tmp.weight.put(weightKey, i);
                    }
                    tmp = cur;
                    for (int k = j; k < m; k++) {
                        String key = new StringBuilder().append('#').append(word.charAt(m - k - 1)).toString();
                        if (!tmp.children.containsKey(key))
                            tmp.children.put(key, new Trie());
                        tmp = tmp.children.get(key);
                        tmp.weight.put(weightKey, i);
                    }
                    String key = new StringBuilder().append(word.charAt(j)).append(word.charAt(m - j - 1)).toString();
                    if (!cur.children.containsKey(key))
                        cur.children.put(key, new Trie());
                    cur = cur.children.get(key);
                    cur.weight.put(weightKey, i);
                }
            }
        }

        public int f(String pref, String suff) {
            Trie cur = trie;
            int m = Math.max(pref.length(), suff.length());
            for (int i = 0; i < m; i++) {
                char c1 = i < pref.length() ? pref.charAt(i) : '#';
                char c2 = i < suff.length() ? suff.charAt(suff.length() - 1 - i) : '#';
                String key = new StringBuilder().append(c1).append(c2).toString();
                if (!cur.children.containsKey(key))
                    return -1;
                cur = cur.children.get(key);
            }
            return cur.weight.get(weightKey);
        }
    }

    private class Trie {

        Map<String, Trie> children;
        Map<String, Integer> weight;

        public Trie() {
            children = new HashMap<String, Trie>();
            weight = new HashMap<String, Integer>();
        }
    }
}
