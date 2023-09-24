package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2023-05-19 7:31
 */
public class Number2059 {

    // 参考：https://leetcode.cn/problems/minimum-operations-to-convert-number/solution/gong-shui-san-xie-shuang-xiang-bfs-mo-ba-uckg/
    public int minimumOperations1(int[] nums, int start, int goal) {

        Deque<Integer> d = new ArrayDeque<Integer>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        d.addLast(start);
        map.put(start, 0);

        while (!d.isEmpty()) {
            int cur = d.pollFirst();
            int step = map.get(cur);
            for (int i : nums) {
                int[] result = new int[]{cur + i, cur - i, cur ^ i};
                for (int next : result) {
                    if (next == goal)
                        return step + 1;
                    if (next < 0 || next > 1000)
                        continue;
                    if (map.containsKey(next))
                        continue;
                    map.put(next, step + 1);
                    d.addLast(next);
                }
            }
        }
        return -1;
    }

    int[] nums;

    // 参考：https://leetcode.cn/problems/minimum-operations-to-convert-number/solution/gong-shui-san-xie-shuang-xiang-bfs-mo-ba-uckg/
    public int minimumOperations2(int[] nums, int start, int goal) {

        this.nums = nums;
        Deque<Long> d1 = new ArrayDeque<Long>();
        Deque<Long> d2 = new ArrayDeque<Long>();
        Map<Long, Integer> m1 = new HashMap<Long, Integer>();
        Map<Long, Integer> m2 = new HashMap<Long, Integer>();
        d1.addLast(start * 1L);
        d2.addLast(goal * 1L);
        m1.put(start * 1L, 0);
        m2.put(goal * 1L, 0);

        while (!d1.isEmpty() && !d2.isEmpty()) {
            if (d1.size() < d2.size()) {
                int ans = update(d1, m1, d2, m2, true);
                if (ans != -1)
                    return ans;
            } else {
                int ans = update(d2, m2, d1, m1, false);
                if (ans != -1)
                    return ans;
            }
        }
        return -1;
    }

    private int update(Deque<Long> d1, Map<Long, Integer> m1, Deque<Long> d2, Map<Long, Integer> m2, boolean flag) {

        int m = d1.size();
        while (m-- > 0) {
            long cur = d1.pollFirst();
            int step = m1.get(cur);
            for (int i : nums) {
                if (flag) {
                    // 正向搜索：进行出队检查，只有出队元素符合条件，才能使用出队元素往下拓展
                    // 使用队列 d1 实现从 start 到 goal 的通路搜索，为满足「 0 <= x <= 1000」的条件限制
                    // 需要进行「出队检查」，只有满足「 0 <= x < =1000」的出队元素，才进行下一步的拓展
                    if (0 <= cur && cur <= 1000) {
                        long[] result = new long[]{cur + i, cur - i, cur ^ i};
                        for (long next : result) {
                            if (m2.containsKey(next))
                                return step + 1 + m2.get(next);
                            if (!m1.containsKey(next)) {
                                d1.addLast(next);
                                m1.put(next, step + 1);
                            }
                        }
                    }
                } else {
                    // 反向搜索：进行入队检查，只有拓展元素符合条件，才能将拓展元素入队
                    // 反向搜索：使用队列 d2 实现从 goal 到 start 的通路搜索，为满足「 0 <= x <= 1000」的条件限制
                    // 需要进行「入队检查」，只有下一个拓展元素 next 满足「 0 <= x <= 1000」才能进行入队
                    long[] result = new long[]{cur + i, cur - i, cur ^ i};
                    for (long next : result) {
                        if (0 <= next && next <= 1000) {
                            if (m2.containsKey(next))
                                return step + 1 + m2.get(next);
                            if (!m1.containsKey(next)) {
                                d1.addLast(next);
                                m1.put(next, step + 1);
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

}
