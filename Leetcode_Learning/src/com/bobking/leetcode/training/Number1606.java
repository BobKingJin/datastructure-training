package com.bobking.leetcode.training;

import java.util.*;

public class Number1606 {

    int N = 100010;
    // 用一个数组 cnts 来充当哈希表，同时维护一个当前处理的最大任务数量 max，最终所有满足 cnst[i] = max 的机台集合即是答案
    int[] cnts = new int[N];

    // 参考：https://leetcode-cn.com/problems/find-servers-that-handled-most-number-of-requests/solution/by-ac_oier-zgm6/
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        Arrays.fill(cnts, 0);
        int n = arrival.length;
        int max = 0;
        // 利用「每个任务有对应的开始时间和持续时间」，使用优先队列维护二元组 (idx, endTime)
        // 其中 idx 为机器编号，endTime 为当前机台所处理任务的结束时间（也就是该机台最早能够接受新任务的时刻）
        // 对于每个 arrival[i] 而言（新任务），先从优先队列中取出所有 endTime ⩽ arrival[i] 的机台 idx，加入「空闲池」
        // 然后再按照「任务分配规则」从空闲池子中取，若取不到，则丢弃该任务
        // 由于「任务分配规则」是优先取大于等于 i % k 的最小值，若取不到，再取大于等于 0 的最小值
        // 因此「空闲池」最好是支持「二分」的有序集合，容易想到基于「红黑树」的 TreeSet 结构
        // 小根堆
        PriorityQueue<int[]> busy = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
        TreeSet<Integer> free = new TreeSet<Integer>();

        for (int i = 0; i < k; i++)
            free.add(i);

        for (int i = 0; i < n; i++) {

            int start = arrival[i];
            int end = start + load[i];

            while (!busy.isEmpty() && busy.peek()[1] <= start)
                free.add(busy.poll()[0]);

            Integer u = free.ceiling(i % k);

            if (u == null)
                u = free.ceiling(0);

            if (u == null)
                continue;

            free.remove(u);
            busy.add(new int[]{u, end});
            max = Math.max(max, ++cnts[u]);
        }

        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < k; i++)
            if (cnts[i] == max) 
                res.add(i);

        return res;
    }
    



}
