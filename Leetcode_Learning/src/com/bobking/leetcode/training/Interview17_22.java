package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2021-06-19 12:04
 */
public class Interview17_22 {

    // 参考：程序猿代码指南P281
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        wordList.add(beginWord);
        // 得到所有单词的 next 节点(即将当前字符仅改变其中 1 个字符得到的)
        HashMap<String, ArrayList<String>> nexts = getNexts(wordList);
        // 得到当前单词到其它单词的最短的距离
        HashMap<String, Integer> distances = getDistances(beginWord, nexts);
        LinkedList<String> pathList = new LinkedList<String>();
        List<List<String>> res = new ArrayList<List<String>>();
        // 得到最短路径并输出最短路径
        getShortestPath(beginWord, endWord, nexts, distances, pathList, res);
        return res;
    }

    private HashMap<String, ArrayList<String>> getNexts(List<String> words) {

        // 把每个单词放到 set 集合中去重，并得到每个单词的邻居节点
        HashSet<String> dict = new HashSet<String>(words);
        HashMap<String, ArrayList<String>> nexts = new HashMap<String, ArrayList<String>>();

        for (int i = 0; i < words.size(); i++)
            nexts.put(words.get(i), new ArrayList<String>());

        for (int i = 0; i < words.size(); i++)
            nexts.put(words.get(i), getNext(words.get(i), dict));

        return nexts;
    }

    // 求单个单词对应的 next 节点
    private ArrayList<String> getNext(String word, HashSet<String> dict) {

        ArrayList<String> res = new ArrayList<String>();
        char[] chs = word.toCharArray();

        // a - z依次与每个位置的值进行替换，组合成新的单词，并查看该单词在字典中是否存在
        for (char cur = 'a'; cur <= 'z'; cur++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] != cur) {
                    char tmp = chs[i];
                    chs[i] = cur;
                    if (dict.contains(String.valueOf(chs)))
                        res.add(String.valueOf(chs));
                    // 注意：最后要换回来
                    chs[i] = tmp;
                }
            }
        }

        return res;
    }

    // 求头节点到每个单词的距离，宽度优先遍历
    private HashMap<String, Integer> getDistances(String beginWord, HashMap<String, ArrayList<String>> nexts) {

        HashMap<String, Integer> distances = new HashMap<String, Integer>();
        distances.put(beginWord, 0);
        // 采用队列，先进先出
        Queue<String> queue = new LinkedList<String>();
        queue.add(beginWord);
        HashSet<String> set = new HashSet<String>();
        set.add(beginWord);

        while (!queue.isEmpty()) {
            String cur = queue.poll();
            for (String str : nexts.get(cur)) {
                if (!set.contains(str)) {
                    set.add(str);
                    queue.add(str);
                    distances.put(str, distances.get(cur) + 1);
                }
            }
        }

        return distances;
    }

    // 最短路径，深度优先遍历
    private void getShortestPath(String cur, String to, HashMap<String, ArrayList<String>> nexts,
                                 HashMap<String, Integer> distances, LinkedList<String> solution,
                                 List<List<String>> res) {
        solution.add(cur);
        if (cur.equals(to)) {
            res.add(new LinkedList<String>(solution));
        } else {
            for (String next : nexts.get(cur)) {
                if (distances.get(next) == distances.get(cur) + 1)
                    getShortestPath(next, to, nexts, distances, solution, res);
            }
        }
        // 回溯
        solution.pollLast();
    }
}
