package com.bobking.leetcode.training;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author BobKing
 * @create 2023-04-20 8:04
 */
public class Number2462 {

    // 参考：https://leetcode.cn/problems/total-cost-to-hire-k-workers/solution/liang-ge-zui-xiao-dui-mo-ni-by-endlessch-nagm/
    public long totalCost(int[] costs, int k, int candidates) {

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Comparator.<Integer>comparingInt(i -> costs[i]).thenComparingInt(i -> i));
        int n = costs.length;
        int l = 0;
        int r = n - 1;
        while (l <= r && l < candidates)
            queue.offer(l++);

        while (r >= l && r >= n - candidates)
            queue.offer(r--);

        long ans = 0;
        int i = 0;

        while (i < k){
            int poll = queue.poll();
            ans += costs[poll];
            if(l <= r) {
                if(poll < l){
                    queue.offer(l++);
                }else {
                    queue.offer(r--);
                }
            }
            i++;
        }
        return ans;
    }
}
