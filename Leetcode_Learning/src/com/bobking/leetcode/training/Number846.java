package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author BobKing
 * @create 2022-09-09 15:38
 */
public class Number846 {

    // 参考：https://leetcode.cn/problems/hand-of-straights/solution/gong-shui-san-xie-shu-ju-jie-gou-mo-ni-t-4hxw/
    public boolean isNStraightHand(int[] hand, int groupSize) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        // 小根堆
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> a - b);
        for (int i : hand) {
            map.put(i, map.getOrDefault(i, 0) + 1);
            q.add(i);
        }

        while (!q.isEmpty()) {
            // 每次从优先队列（堆）中取出堆顶元素 t 来 尝试作为「顺子」的发起点/首个元素
            int t = q.poll();

            if (map.get(t) == 0)
                continue;

            for (int i = 0; i < groupSize; i++) {

                int cnt = map.getOrDefault(t + i, 0);

                if (cnt == 0)
                    return false;

                map.put(t + i, cnt - 1);
            }
        }

        return true;
    }
}
