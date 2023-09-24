package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-08-27 8:23
 */
public class Number433 {

    char[] items = new char[]{'A', 'C', 'G', 'T'};

    // 参考：https://leetcode.cn/problems/minimum-genetic-mutation/solution/by-ac_oier-74b4/
    public int minMutation1(String start, String end, String[] bank) {

        Set<String> set = new HashSet<String>();
        for (String s : bank)
            set.add(s);

        Deque<String> d = new ArrayDeque<String>();
        // 使用「哈希表」记录到达某个状态所消耗的步数
        Map<String, Integer> map = new HashMap<String, Integer>();
        // 起始将 start 加入队列，并更新到达 start 所使用的步数为 0，然后进行常规的 BFS 过程：
        // 每次取出队头元素，尝试替换当前状态的某一位，来得到新的状态（限定新状态必须合法，即必须出现在 Set 中）
        // 如果新状态合法并且没有在记录步数的哈希表中出现过，则将新状态入队并更新得到新状态所用步数，否则丢弃新状态
        d.addLast(start);
        map.put(start, 0);
        while (!d.isEmpty()) {
            int size = d.size();
            while (size-- > 0) {
                String s = d.pollFirst();
                char[] cs = s.toCharArray();
                int step = map.get(s);

                for (int i = 0; i < 8; i++) {
                    for (char c : items) {
                        if (cs[i] == c)
                            continue;
                        char[] clone = cs.clone();
                        clone[i] = c;
                        String sub = String.valueOf(clone);
                        if (!set.contains(sub))
                            continue;
                        if (map.containsKey(sub))
                            continue;
                        if (sub.equals(end))
                            return step + 1;
                        map.put(sub, step + 1);
                        d.addLast(sub);
                    }
                }
            }
        }
        return -1;
    }

    Set<String> hashSet = new HashSet<String>();

    // 参考：https://leetcode.cn/problems/minimum-genetic-mutation/solution/by-ac_oier-74b4/
    public int minMutation2(String start, String end, String[] bank) {

        hashSet.add(start);
        for (String s : bank)
            hashSet.add(s);

        if (!hashSet.contains(end))
            return -1;

        Deque<String> d1 = new ArrayDeque<String>();
        Deque<String> d2 = new ArrayDeque<String>();
        d1.addLast(start);
        d2.addLast(end);
        Map<String, Integer> m1 = new HashMap<String, Integer>();
        Map<String, Integer> m2 = new HashMap<String, Integer>();
        m1.put(start, 0);
        m2.put(end, 0);

        while (!d1.isEmpty() && !d2.isEmpty()) {
            int t = -1;
            if (d1.size() <= d2.size()) {
                t = update(d1, m1, m2);
            } else {
                t = update(d2, m2, m1);
            }
            if (t != -1)
                return t;
        }
        return -1;
    }

    private int update(Deque<String> d, Map<String, Integer> cur, Map<String, Integer> other) {
        int m = d.size();
        while (m-- > 0) {
            String s = d.pollFirst();
            char[] cs = s.toCharArray();
            int step = cur.get(s);
            for (int i = 0; i < 8; i++) {
                for (char c : items) {
                    if (cs[i] == c)
                        continue;
                    char[] clone = cs.clone();
                    clone[i] = c;
                    String sub = String.valueOf(clone);
                    if (!hashSet.contains(sub) || cur.containsKey(sub))
                        continue;
                    if (other.containsKey(sub))
                        return other.get(sub) + step + 1;
                    d.addLast(sub);
                    cur.put(sub, step + 1);
                }
            }
        }
        return -1;
    }
}
