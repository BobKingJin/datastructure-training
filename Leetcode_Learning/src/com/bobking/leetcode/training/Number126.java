package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2023-09-24 9:20
 */
public class Number126 {

    // 参考：https://leetcode.cn/problems/word-ladder-ii/solutions/22295/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-3-3/
    public List<List<String>> findLadders1(String beginWord, String endWord, List<String> wordList) {

        List<List<String>> ans = new ArrayList<List<String>>();

        if (!wordList.contains(endWord))
            return ans;

        // 利用 BFS 得到所有的邻居节点
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        bfs(beginWord, endWord, wordList, map);
        ArrayList<String> temp = new ArrayList<String>();
        // temp 用来保存当前的路径
        temp.add(beginWord);
        findLaddersHelper(beginWord, endWord, map, temp, ans);
        return ans;
    }

    private void findLaddersHelper(String beginWord, String endWord, HashMap<String, ArrayList<String>> map, ArrayList<String> temp, List<List<String>> ans) {

        if (beginWord.equals(endWord)) {
            ans.add(new ArrayList<String>(temp));
            return;
        }
        // 得到所有的下一个的节点
        ArrayList<String> neighbors = map.getOrDefault(beginWord, new ArrayList<String>());

        for (String neighbor : neighbors) {
            temp.add(neighbor);
            findLaddersHelper(neighbor, endWord, map, temp, ans);
            temp.remove(temp.size() - 1);
        }
    }

    private void bfs(String beginWord, String endWord, List<String> wordList, HashMap<String, ArrayList<String>> map) {

        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        boolean isFound = false;
        int depth = 0;
        Set<String> dict = new HashSet<String>(wordList);
        Set<String> visited = new HashSet<String>();
        visited.add(beginWord);

        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            Set<String> subVisited = new HashSet<String>();
            for (int j = 0; j < size; j++) {
                String temp = queue.poll();
                // 一次性得到所有的下一个的节点
                ArrayList<String> neighbors = getNeighbors(temp, dict);
                Iterator<String> it = neighbors.iterator();
                while (it.hasNext()) {
                    String neighbor = it.next();
                    if (!visited.contains(neighbor)) {
                        if (neighbor.equals(endWord))
                            isFound = true;
                        queue.offer(neighbor);
                        subVisited.add(neighbor);
                    } else {
                        it.remove();
                    }
                }
                map.put(temp, neighbors);
            }
            visited.addAll(subVisited);
            if (isFound)
                break;
        }
    }

    private ArrayList<String> getNeighbors(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        char chs[] = node.toCharArray();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch)
                    continue;
                char oldCh = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs)))
                    res.add(String.valueOf(chs));
                chs[i] = oldCh;
            }
        }
        return res;
    }

    // 参考：https://leetcode.cn/problems/word-ladder-ii/solutions/22295/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-3-3/
    public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {

        List<List<String>> ans = new ArrayList<List<String>>();

        // 如果不含有结束单词，直接结束，不然后边会造成死循环
        if (!wordList.contains(endWord))
            return ans;

        bfs(beginWord, endWord, wordList, ans);
        return ans;
    }


    public void bfs(String beginWord, String endWord, List<String> wordList, List<List<String>> ans) {

        Queue<List<String>> queue = new LinkedList<List<String>>();
        List<String> path = new ArrayList<String>();
        path.add(beginWord);
        queue.offer(path);
        boolean isFound = false;
        Set<String> dict = new HashSet<String>(wordList);
        Set<String> visited = new HashSet<String>();
        visited.add(beginWord);

        while (!queue.isEmpty()) {

            int size = queue.size();
            Set<String> subVisited = new HashSet<String>();
            for (int j = 0; j < size; j++) {
                List<String> p = queue.poll();
                // 得到当前路径的末尾单词
                String temp = p.get(p.size() - 1);
                // 一次性得到所有的下一个的节点
                ArrayList<String> neighbors = getNeighbors(temp, dict);
                for (String neighbor : neighbors) {
                    //只考虑之前没有出现过的单词
                    if (!visited.contains(neighbor)) {
                        //到达结束单词
                        if (neighbor.equals(endWord)) {
                            isFound = true;
                            p.add(neighbor);
                            ans.add(new ArrayList<String>(p));
                            p.remove(p.size() - 1);
                        }

                        //加入当前单词
                        p.add(neighbor);
                        queue.offer(new ArrayList<String>(p));
                        p.remove(p.size() - 1);
                        subVisited.add(neighbor);
                    }
                }
            }
            visited.addAll(subVisited);
            if (isFound)
                break;
        }
    }

    // 参考：https://leetcode.cn/problems/word-ladder-ii/solutions/22295/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-3-3/
    public List<List<String>> findLadders3(String beginWord, String endWord, List<String> wordList) {

        List<List<String>> ans = new ArrayList<List<String>>();

        if (!wordList.contains(endWord))
            return ans;

        // 利用 BFS 得到所有的邻居节点
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        bfs2(beginWord, endWord, wordList, map);

        ArrayList<String> temp = new ArrayList<String>();
        // temp 用来保存当前的路径
        temp.add(beginWord);
        findLaddersHelper2(beginWord, endWord, map, temp, ans);
        return ans;
    }

    private void findLaddersHelper2(String beginWord, String endWord, HashMap<String, ArrayList<String>> map, ArrayList<String> temp, List<List<String>> ans) {

        if (beginWord.equals(endWord)) {
            ans.add(new ArrayList<String>(temp));
            return;
        }

        // 得到所有的下一个的节点
        ArrayList<String> neighbors = map.getOrDefault(beginWord, new ArrayList<String>());

        for (String neighbor : neighbors) {
            temp.add(neighbor);
            findLaddersHelper2(neighbor, endWord, map, temp, ans);
            temp.remove(temp.size() - 1);
        }
    }

    // 利用递归实现了双向搜索
    private void bfs2(String beginWord, String endWord, List<String> wordList, HashMap<String, ArrayList<String>> map) {

        Set<String> set1 = new HashSet<String>();
        set1.add(beginWord);
        Set<String> set2 = new HashSet<String>();
        set2.add(endWord);
        Set<String> wordSet = new HashSet<String>(wordList);
        bfsHelper(set1, set2, wordSet, true, map);
    }

    // direction 为 true 代表向下扩展，false 代表向上扩展
    private boolean bfsHelper(Set<String> set1, Set<String> set2, Set<String> wordSet, boolean direction, HashMap<String, ArrayList<String>> map) {

        // set1 为空了，就直接结束
        // 比如下边的例子就会造成 set1 为空
        /*	"hot" "dog" ["hot","dog"]*/
        if (set1.isEmpty())
            return false;
        // set1 的数量多，就反向扩展
        if (set1.size() > set2.size())
            return bfsHelper(set2, set1, wordSet, !direction, map);
        // 将已经访问过单词删除
        wordSet.removeAll(set1);
        wordSet.removeAll(set2);
        boolean done = false;
        // 保存新扩展得到的节点
        Set<String> set = new HashSet<String>();
        for (String str : set1) {
            // 遍历每一位
            for (int i = 0; i < str.length(); i++) {
                char[] chars = str.toCharArray();
                // 尝试所有字母
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    if (chars[i] == ch)
                        continue;
                    chars[i] = ch;
                    String word = new String(chars);
                    // 根据方向得到 map 的 key 和 val
                    String key = direction ? str : word;
                    String val = direction ? word : str;
                    ArrayList<String> list = map.containsKey(key) ? map.get(key) : new ArrayList<String>();
                    // 如果相遇了就保存结果
                    if (set2.contains(word)) {
                        done = true;
                        list.add(val);
                        map.put(key, list);
                    }
                    // 如果还没有相遇，并且新的单词在 word 中，那么就加到 set 中
                    if (!done && wordSet.contains(word)) {
                        set.add(word);
                        list.add(val);
                        map.put(key, list);
                    }
                }
            }
        }

        // 一般情况下新扩展的元素会多一些，所以下次反方向扩展  set2
        return done || bfsHelper(set2, set, wordSet, !direction, map);
    }

    // 参考：https://leetcode.cn/problems/word-ladder-ii/solutions/277612/yan-du-you-xian-bian-li-shuang-xiang-yan-du-you--2/
    public List<List<String>> findLadders4(String beginWord, String endWord, List<String> wordList) {

        List<List<String>> res = new ArrayList<List<String>>();
        Set<String> dict = new HashSet<String>(wordList);
        if (!dict.contains(endWord))
            return res;

        dict.remove(beginWord);

        Map<String, Integer> steps = new HashMap<String, Integer>();
        steps.put(beginWord, 0);
        Map<String, Set<String>> from = new HashMap<String, Set<String>>();
        boolean found = bfs(beginWord, endWord, dict, steps, from);

        if (found) {
            Deque<String> path = new ArrayDeque<String>();
            path.add(endWord);
            dfs(from, path, beginWord, endWord, res);
        }
        return res;
    }


    private boolean bfs(String beginWord, String endWord, Set<String> dict, Map<String, Integer> steps, Map<String, Set<String>> from) {

        int wordLen = beginWord.length();
        int step = 0;
        boolean found = false;

        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                char[] charArray = currWord.toCharArray();
                for (int j = 0; j < wordLen; j++) {
                    char origin = charArray[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        charArray[j] = c;
                        String nextWord = String.valueOf(charArray);
                        if (steps.containsKey(nextWord) && steps.get(nextWord) == step)
                            from.get(nextWord).add(currWord);
                        if (!dict.contains(nextWord))
                            continue;
                        dict.remove(nextWord);
                        queue.offer(nextWord);
                        from.putIfAbsent(nextWord, new HashSet<String>());
                        from.get(nextWord).add(currWord);
                        steps.put(nextWord, step);
                        if (nextWord.equals(endWord))
                            found = true;
                    }
                    charArray[j] = origin;
                }
            }
            if (found) {
                break;
            }
        }
        return found;
    }

    private void dfs(Map<String, Set<String>> from, Deque<String> path, String beginWord, String cur, List<List<String>> res) {
        if (cur.equals(beginWord)) {
            res.add(new ArrayList<String>(path));
            return;
        }
        for (String precursor : from.get(cur)) {
            path.addFirst(precursor);
            dfs(from, path, beginWord, precursor, res);
            path.removeFirst();
        }
    }
}
