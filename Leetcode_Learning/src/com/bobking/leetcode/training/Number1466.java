package com.bobking.leetcode.training;

import java.util.*;

/**
 * @Date: 2023/12/31 16:13
 * @Author: BobKing
 * @Description:
 */
public class Number1466 {

    // 参考: https://leetcode.cn/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/solutions/268785/reorder-routes-by-ikaruga/?envType=daily-question&envId=2023-12-31
    public int minReorder(int n, int[][] connections) {

        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<Integer>());

        for (int i = 0; i < n - 1; i++) {
            int[] c = connections[i];
            int x = c[0];
            int y = c[1];
            g[x].add(i);
            g[y].add(i);
        }

        Deque<Integer> q = new LinkedList<Integer>();
        q.offer(0);
        int ans = 0;
        //用来标记是否访问过
        boolean[] visited = new boolean[n];

        while (!q.isEmpty()) {
            int x = q.poll();
            for (int idx : g[x]) {
                if (visited[idx])
                    continue;
                visited[idx] = true;
                int a = connections[idx][0];
                int b = connections[idx][1];
                ans += (a == x ? 1 : 0);
                a = (a == x ? b : a);
                q.offer(a);
            }
        }
        return ans;
    }
}
