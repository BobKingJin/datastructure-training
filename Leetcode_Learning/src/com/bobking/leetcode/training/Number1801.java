package com.bobking.leetcode.training;

import java.util.PriorityQueue;

/**
 * @author BobKing
 * @create 2023-02-18 12:27
 */
public class Number1801 {

    // 参考：https://leetcode.cn/problems/number-of-orders-in-the-backlog/solution/by-lcbin-vf1s/
    public int getNumberOfBacklogOrders(int[][] orders) {

        PriorityQueue<int[]> buy = new PriorityQueue<int[]>((a, b) -> b[0] - a[0]);
        PriorityQueue<int[]> sell = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        for (int[] e : orders) {
            int p = e[0];
            int a = e[1];
            int t = e[2];
            if (t == 0) {
                while (a > 0 && !sell.isEmpty() && sell.peek()[0] <= p) {
                    int[] q = sell.poll();
                    int x = q[0];
                    int y = q[1];
                    if (a >= y) {
                        a -= y;
                    } else {
                        sell.offer(new int[] {x, y - a});
                        a = 0;
                    }
                }
                if (a > 0)
                    buy.offer(new int[] {p, a});
            } else {
                while (a > 0 && !buy.isEmpty() && buy.peek()[0] >= p) {
                    int[] q = buy.poll();
                    int x = q[0];
                    int y = q[1];
                    if (a >= y) {
                        a -= y;
                    } else {
                        buy.offer(new int[] {x, y - a});
                        a = 0;
                    }
                }
                if (a > 0)
                    sell.offer(new int[] {p, a});
            }
        }
        long ans = 0;
        final int mod = (int) 1e9 + 7;
        while (!buy.isEmpty())
            ans += buy.poll()[1];

        while (!sell.isEmpty())
            ans += sell.poll()[1];

        return (int) (ans % mod);
    }
}
