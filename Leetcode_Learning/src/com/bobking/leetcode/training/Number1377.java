package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author BobKing
 * @create 2023-05-24 7:06
 */
public class Number1377 {

    private class Node {
        int id;
        double p;
        int t;

        public Node(int i, double pp, int tt) {
            this.id = i;
            this.p = pp;
            this.t = tt;
        }
    }

    public double frogPosition(int n, int[][] edges, int t, int target) {

        // 就算是当前已经到达 target，只要还有其他没有访问过的点，还是会继续跳，直到时间用完

        Set<Integer>[] sets = new Set[n + 1];
        for (int i = 0; i <= n; i++)
            sets[i] = new HashSet<Integer>();
        for (int[] e : edges) {
            sets[e[0]].add(e[1]);
            sets[e[1]].add(e[0]);
        }

        Queue<Node> q = new LinkedList<Node>();
        q.add(new Node(1, 1.000000, 0));
        boolean[] vis = new boolean[n + 1];
        vis[1] = true;

        while (!q.isEmpty()) {

            Node u = q.poll();

            if (u.t == t && u.id == target)
                return u.p;

            int sz = 0;
            for (int nb : sets[u.id]) {
                if (!vis[nb])
                    sz++;
            }

            if (u.t < t) {
                boolean find = false;
                for (int nb : sets[u.id]) {
                    if (vis[nb])
                        continue;
                    find = true;
                    vis[nb] = true;
                    q.add(new Node(nb, u.p / sz, u.t + 1));
                }

                if (find == false)
                    q.add(new Node(u.id, u.p, u.t + 1));
            }

        }
        return 0.0;
    }
}
