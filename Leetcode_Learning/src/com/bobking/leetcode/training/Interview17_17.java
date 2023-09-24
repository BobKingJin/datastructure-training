package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-10-28 10:19
 */
public class Interview17_17 {

    private class TrieNode{
        String end;
        TrieNode[] next = new TrieNode[26];
    }

    private class Trie{

        TrieNode root;

        public Trie(String[] words){
            root = new TrieNode();
            for(String word : words){
                TrieNode node = root;
                for(char w : word.toCharArray()){
                    int i = w - 'a';
                    if(node.next[i] == null)
                        node.next[i] = new TrieNode();

                    node = node.next[i];
                }
                node.end = word;
            }
        }

        public List<String> search(String str){
            TrieNode node = root;
            List<String> res = new ArrayList<String>();
            for(char c : str.toCharArray()){
                int i = c - 'a';

                if(node.next[i] == null)
                    break;

                node = node.next[i];
                if(node.end != null)
                    res.add(node.end);
            }
            return res;
        }
    }

    // 参考：https://leetcode.cn/problems/multi-search-lcci/solution/mian-shi-ti-1717-duo-ci-sou-suo-python-j-61is/
    public int[][] multiSearch(String big, String[] smalls) {

        Trie trie = new Trie(smalls);
        Map<String, List<Integer>> hit = new HashMap<String, List<Integer>>();

        for(int i = 0; i < big.length(); i++){
            List<String> matchs = trie.search(big.substring(i));
            for(String word: matchs){
                if(!hit.containsKey(word))
                    hit.put(word, new ArrayList<Integer>());

                hit.get(word).add(i);
            }
        }

        int[][] res = new int[smalls.length][];
        for(int i = 0; i < smalls.length; i++){
            List<Integer> list = hit.get(smalls[i]);
            if(list == null){
                res[i] = new int[0];
                continue;
            }
            int size = list.size();
            res[i] = new int[size];
            for(int j = 0; j < size; j++)
                res[i][j] = list.get(j);
        }
        return res;
    }
}
