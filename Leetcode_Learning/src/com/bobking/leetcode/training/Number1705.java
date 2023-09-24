package com.bobking.leetcode.training;

import java.util.PriorityQueue;

/**
 * @author BobKing
 * @create 2022-09-17 10:43
 */
public class Number1705 {

    // 参考：https://leetcode.cn/problems/maximum-number-of-eaten-apples/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-hfdy0/
    public int eatenApples(int[] apples, int[] days) {

        // 直觉上，会觉得「优先吃掉最快过期的苹果」会是最优，而这个维护苹果过期的过程，可以使用「小根堆」来实现
        // 以二元组 (最后食用日期, 当日产生苹果数量) 的形式存入「小根堆」进行维护
        // 令 n 为数组长度，time 为当前时间，ans 为吃到的苹果数量
        // 首先，如果「time < n」或者「堆不为空」，说明「还有苹果未被生成」或者「未必吃掉」，继续模拟
        // 在当日模拟中，如果「time < n」，说明当天有苹果生成，先将苹果 以二元组 (time + days[time] - 1, apples[time]) 形式加入小根堆中
        // 其中二元组表示 (最后食用日期, 当日产生苹果数量)，同时需要过滤 apples[time] = 0 的情况
        // 然后尝试从堆中取出「最后食用日期」最早「可食用」的苹果 cur，如果堆顶元素已过期，则抛弃
        // 如果吃掉 cur 一个苹果后，仍有剩余，并且最后时间大于当前时间（尚未过期），将 cur 重新入堆
        // 循环上述逻辑，直到所有苹果出堆

        // 小根堆
        PriorityQueue<int[]> q = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        int n = apples.length;
        int time = 0;
        int ans = 0;

        while (time < n || !q.isEmpty()) {

            if (time < n && apples[time] > 0)
                q.add(new int[]{time + days[time] - 1, apples[time]});

            while (!q.isEmpty() && q.peek()[0] < time)
                q.poll();

            if (!q.isEmpty()) {

                int[] cur = q.poll();

                // 重新入堆
                if (--cur[1] > 0 && cur[0] > time)
                    q.add(cur);

                ans++;
            }
            time++;
        }
        return ans;
    }
}
