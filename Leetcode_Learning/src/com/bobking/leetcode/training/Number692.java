package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-08-07 10:01
 */
public class Number692 {

    // 参考：https://leetcode.cn/problems/top-k-frequent-words/solution/gong-shui-san-xie-xiang-jie-shi-yong-ha-8dxt2/
    public List<String> topKFrequent(String[] words, int k) {

        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String w : words)
            map.put(w, map.getOrDefault(w, 0) + 1);

        PriorityQueue<Object[]> q = new PriorityQueue<>(k, (a, b)->{
            // 如果词频不同，根据词频升序
            int c1 = (Integer)a[0];
            int c2 = (Integer)b[0];
            if (c1 != c2)
                return c1 - c2;
            // 如果词频相同，根据字典序倒序
            String s1 = (String)a[1];
            String s2 = (String)b[1];
            return s2.compareTo(s1);
        });

        for (String s : map.keySet()) {

            int cnt = map.get(s);
            // 不足 k 个，直接入堆
            if (q.size() < k) {
                q.add(new Object[]{cnt, s});
            } else {
                Object[] peek = q.peek();
                // 词频比堆顶元素大，弹出堆顶元素，入堆
                if (cnt > (Integer)peek[0]) {
                    q.poll();
                    q.add(new Object[]{cnt, s});
                           // 词频与堆顶元素相同
                } else if (cnt == (Integer)peek[0]) {
                    String top = (String)peek[1];
                       // 且字典序大小比堆顶元素小，弹出堆顶元素，入堆
                    if (s.compareTo(top) < 0) {
                        q.poll();
                        q.add(new Object[]{cnt, s});
                    }
                }
            }
        }

        List<String> ans = new ArrayList<String>();
        while (!q.isEmpty())
            ans.add((String)q.poll()[1]);
        Collections.reverse(ans);
        return ans;
    }
}
