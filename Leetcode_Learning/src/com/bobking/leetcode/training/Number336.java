package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Number336 {

    // 字典树
    private class Node {

        int[] ch = new int[26];
        int flag;

        public Node() {
            flag = -1;
        }
    }

    List<Node> tree = new ArrayList<Node>();

    // 参考：https://leetcode.cn/problems/palindrome-pairs/solution/hui-wen-dui-by-leetcode-solution/
    public List<List<Integer>> palindromePairs(String[] words) {

        // 空串
        tree.add(new Node());
        int n = words.length;
        for (int i = 0; i < n; i++)
            insert(words[i], i);

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            int m = words[i].length();
            for (int j = 0; j <= m; j++) {
                if (isPalindrome(words[i], j, m - 1)) {
                    int leftId = findWord(words[i], 0, j - 1);
                    if (leftId != -1 && leftId != i)
                        res.add(Arrays.asList(i, leftId));
                }

                if (j != 0 && isPalindrome(words[i], 0, j - 1)) {
                    int rightId = findWord(words[i], j, m - 1);
                    if (rightId != -1 && rightId != i)
                        res.add(Arrays.asList(rightId, i));
                }
            }
        }

        return res;
    }

    private void insert(String s, int id) {

        int len = s.length();
        int add = 0;
        for (int i = 0; i < len; i++) {
            int x = s.charAt(i) - 'a';
            if (tree.get(add).ch[x] == 0) {
                tree.add(new Node());
                tree.get(add).ch[x] = tree.size() - 1;
            }
            add = tree.get(add).ch[x];
        }
        tree.get(add).flag = id;
    }

    private boolean isPalindrome(String s, int left, int right) {

        int len = right - left + 1;
        for (int i = 0; i < len / 2; i++)
            if (s.charAt(left + i) != s.charAt(right - i))
                return false;

        return true;
    }

    // 反过来
    private int findWord(String s, int left, int right) {

        int add = 0;
        for (int i = right; i >= left; i--) {
            int x = s.charAt(i) - 'a';
            if (tree.get(add).ch[x] == 0)
                return -1;
            add = tree.get(add).ch[x];
        }

        return tree.get(add).flag;
    }

}
