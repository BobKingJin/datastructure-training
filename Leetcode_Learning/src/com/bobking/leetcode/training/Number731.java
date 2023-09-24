package com.bobking.leetcode.training;

import java.util.TreeMap;

public class Number731 {

    // 参考：https://leetcode.cn/problems/my-calendar-ii/solution/by-ac_oier-okkc/
    private class MyCalendarTwo1 {

        private class Node {
            // ls/rs: 分别代表当前节点的左右子节点在线段树数组 tr 中的下标
            int ls;
            int rs;
            // add: 懒标记
            int add;
            // max: 为当前区间的最大值
            int max;
        }

        int N = (int)1e9;
        int M = 120010;
        int cnt = 1;
        // 对于常规的线段树实现来说，都是一开始就调用 build 操作创建空树
        // 而线段树一般以「满二叉树」的形式用数组存储，因此需要 4 * n 的空间
        // 并且这些空间在起始 build 空树的时候已经锁死
        Node[] tr = new Node[M];

        public MyCalendarTwo1() {

        }

        private void update(int u, int lc, int rc, int l, int r, int v) {

            if (l <= lc && rc <= r) {
                tr[u].add += v;
                tr[u].max += v;
                return;
            }

            lazyCreate(u);
            pushdown(u);
            int mid = lc + rc >> 1;
            if (l <= mid)
                update(tr[u].ls, lc, mid, l, r, v);

            if (r > mid)
                update(tr[u].rs, mid + 1, rc, l, r, v);

            pushup(u);
        }

        int query(int u, int lc, int rc, int l, int r) {

            if (l <= lc && rc <= r)
                return tr[u].max;

            lazyCreate(u);
            pushdown(u);
            int mid = lc + rc >> 1;
            int ans = 0;
            if (l <= mid)
                ans = Math.max(ans, query(tr[u].ls, lc, mid, l, r));

            if (r > mid)
                ans = Math.max(ans, query(tr[u].rs, mid + 1, rc, l, r));

            return ans;
        }

        private void lazyCreate(int u) {

            if (tr[u] == null)
                tr[u] = new Node();

            if (tr[u].ls == 0) {
                tr[u].ls = ++cnt;
                tr[tr[u].ls] = new Node();
            }

            if (tr[u].rs == 0) {
                tr[u].rs = ++cnt;
                tr[tr[u].rs] = new Node();
            }
        }

        private void pushup(int u) {
            tr[u].max = Math.max(tr[tr[u].ls].max, tr[tr[u].rs].max);
        }

        private void pushdown(int u) {
            tr[tr[u].ls].add += tr[u].add;
            tr[tr[u].rs].add += tr[u].add;
            tr[tr[u].ls].max += tr[u].add;
            tr[tr[u].rs].max += tr[u].add;
            tr[u].add = 0;
        }

        public boolean book(int start, int end) {

            if (query(1, 1, N + 1, start + 1, end) >= 2)
                return false;

            update(1, 1, N + 1, start + 1, end, 1);
            return true;
        }
    }

    // 参考：https://leetcode.cn/problems/my-calendar-ii/solution/wo-de-ri-cheng-an-pai-biao-ii-by-leetcode/
    // 差分数组：https://segmentfault.com/a/1190000040600758
    // 差分数组：https://www.cnblogs.com/COLIN-LIGHTNING/p/8436624.html
    private class MyCalendarTwo2 {

        TreeMap<Integer, Integer> delta;

        public MyCalendarTwo2() {
            delta = new TreeMap();
        }

        // 预定一个新的日程安排 [start, end)，则执行 delta[start]++ 和 delta[end]--
        // 其中 delta 是按照 key 值从小到大排序的结构，用 active 来记录当前正在进行的日程安排数
        // 当 active >= 3 时，说明产生了三重预定
        public boolean book(int start, int end) {

            delta.put(start, delta.getOrDefault(start, 0) + 1);
            delta.put(end, delta.getOrDefault(end, 0) - 1);

            int active = 0;
            for (int d : delta.values()) {
                active += d;
                if (active >= 3) {
                    delta.put(start, delta.get(start) - 1);
                    delta.put(end, delta.get(end) + 1);

                    if (delta.get(start) == 0)
                        delta.remove(start);

                    return false;
                }
            }

            return true;
        }
    }

}
