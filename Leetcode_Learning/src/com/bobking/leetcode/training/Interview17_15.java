package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-06-18 11:42
 */
public class Interview17_15 {

    // 参考：https://leetcode.cn/problems/longest-word-lcci/solution/zui-chang-dan-ci-by-hao-hou-de-yun/
    public String longestWord1(String[] words) {

        // 先对 words 进行排序
        // 先从最长的开始尝试
        Arrays.sort(words, (o1, o2) -> {
            if (o1.length() == o2.length())
                return o1.compareTo(o2);
            else {
                return Integer.compare(o2.length(), o1.length());
            }
        });

        Set<String> set = new HashSet<String>(Arrays.asList(words));
        for (String word : words) {
            set.remove(word);
            if (find(set, word))
                return word;
        }

        return "";
    }

    private boolean find(Set<String> set, String word) {

        if (word.length() == 0)
            return true;

        for (int i = 0; i < word.length(); i++) {
            if (set.contains(word.substring(0, i + 1)) && find(set, word.substring(i + 1)))
                return true;
        }

        return false;
    }

    // 参考：https://leetcode.cn/problems/longest-word-lcci/solution/a-fei-suan-fa-mian-shi-ti-1715-zui-chang-c3vr/
    public String longestWord2(String[] words) {

        String res = "";
        List<String> wordList = Arrays.asList(words);

        wordList.sort((a, b) -> a.length() == b.length() ? a.compareTo(b) : b.length() - a.length());
        for (String target : wordList)
            if (dfs(target, 0, wordList))
                return target;

        return res;
    }

    private boolean dfs(String target, int start, List<String> wordList) {

        if (start == target.length())
            return true;

        for (int end = start; end < target.length(); end++) {

            // 下面这一行是为了排除目标单词 target 本身，题意要求由其他的至少两个单词组成
            // 当遍历的时候只有一轮，一直没找到其他的目标单词，这个目标单词做为一个候选词，需要被排除掉
            if (end - start + 1 == target.length())
                continue;

            // 因为并不涉及到对 target 或者 wordList 的更改，所以不需要回溯
            String prev = target.substring(start, end + 1);
            if (wordList.contains(prev) && dfs(target, end + 1, wordList))
                return true;
        }

        return false;
    }

    // 参考：https://leetcode.cn/problems/longest-word-lcci/solution/a-fei-suan-fa-mian-shi-ti-1715-zui-chang-c3vr/
    public String longestWord3(String[] words) {

        String res = "";
        if (words.length == 0)
            return res;

        List<String> base = Arrays.asList(words);
        for (String target : words) {
            List<String> list = new ArrayList<String>();
            // 不影响 base 本身
            // 复制一份单词列表，并且移除这个目标单词本身
            Collections.addAll(list, new String[base.size()]);
            Collections.copy(list, base);
            list.remove(target);
            if (check1(target, list)) {
                // 有更长的，选更长的
                if (target.length() > res.length()) {
                    res = target;
                    // 单词长度相等，选字典序小的
                } else if (target.length() == res.length() && target.compareTo(res) < 0) {
                    res = target;
                }
            }
        }
        return res;
    }

    private boolean check1(String target, List<String> list) {

        if (target.length() == 0)
            return true;

        for (int i = 0; i <= target.length(); i++) {
            //当前切出来的单词在单词列表中&&剩下的单词也能在单词列表中找到（可能需要再切分）
            if (list.contains(target.substring(0, i)) && check1(target.substring(i), list))
                return true;
        }

        return false;
    }

    public String longestWord(String[] words) {

        String res = "";
        List<String> wordList = Arrays.asList(words);
        wordList.sort((a, b) -> a.length() == b.length() ? a.compareTo(b) : b.length() - a.length());
        for (String target : wordList)
            if (check2(target, wordList))
                return target;

        return res;
    }

    private boolean check2(String target, List<String> wordList) {

        int n = target.length();
        if (n == 0)
            return false;

        boolean[] f = new boolean[n];
        for (int i = 0; i < n; i++) {
            // 排除自身的结果，先切这部分
            if (i < n - 1 && wordList.contains(target.substring(0, i + 1))) {
                f[i] = true;
                continue;
            }

            for (int j = i - 1; j >= 0; j--) {
                if (f[j] && wordList.contains(target.substring(j + 1, i + 1))) {
                    f[i] = true;
                    break;
                }
            }
        }

        return f[n - 1];
    }

    private TrieNode root;

    // 参考：https://leetcode.cn/problems/longest-word-lcci/solution/a-fei-suan-fa-mian-shi-ti-1715-zui-chang-c3vr/
    public String longestWord4(String[] words) {

        root = new TrieNode();
        String res = "";
        List<String> wordList = Arrays.asList(words);

        wordList.sort((a, b) -> a.length() == b.length() ? a.compareTo(b) : b.length() - a.length());
        // 构造字典树
        for (String word : wordList)
            insert(word);

        for (String word : wordList) {

            TrieNode cur = root;
            int n = word.length();
            for (int i = 0; i < n; i++) {
                char c = word.charAt(i);
                // 排除掉自身，当前遍历到的字符是个单词，且剩余部分可以再次被切分
                if (i < n - 1 && cur.children[c - 'a'].isEnd && canSplitToWord(word.substring(i + 1)))
                    return word;

                cur = cur.children[c - 'a'];
            }
        }
        return res;
    }

    private boolean canSplitToWord(String remain) {

        if (remain.equals(""))
            return true;

        TrieNode cur = root;
        for (int i = 0; i < remain.length(); i++) {

            char c = remain.charAt(i);
            if (cur.children[c - 'a'] == null)
                return false;

            if (cur.children[c - 'a'].isEnd && canSplitToWord(remain.substring(i + 1)))
                return true;

            cur = cur.children[c - 'a'];
        }

        return false;
    }

    private void insert(String word) {

        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null)
                cur.children[c - 'a'] = new TrieNode();

            cur = cur.children[c - 'a'];
        }
        cur.isEnd = true;
    }


    private class TrieNode {

        private TrieNode[] children;
        private boolean isEnd;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.isEnd = false;
        }
    }


}
