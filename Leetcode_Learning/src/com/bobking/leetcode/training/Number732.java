package com.bobking.leetcode.training;

import java.util.Map;
import java.util.TreeMap;

public class Number732 {

    // 参考：https://leetcode.cn/problems/my-calendar-iii/solution/by-ac_oier-cv31/
    private class MyCalendarThree1 {

        private class Node {
            int ls;
            int rs;
            int add;
            int max;
        }

        int N = (int)1e9;
        int M = 4 * 400 * 20;
        int cnt = 1;
        Node[] tr = new Node[M];

        public MyCalendarThree1() {

        }

        private void update(int u, int lc, int rc, int l, int r, int v) {

            if (l <= lc && rc <= r) {
                tr[u].add += v;
                tr[u].max += v;
                return ;
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

        private int query(int u, int lc, int rc, int l, int r) {

            if (l <= lc && rc <= r)
                return tr[u].max;

            lazyCreate(u);
            pushdown(u);
            int mid = lc + rc >> 1;
            int ans = 0;
            if (l <= mid)
                ans = query(tr[u].ls, lc, mid, l, r);

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

        private void pushdown(int u) {
            tr[tr[u].ls].add += tr[u].add;
            tr[tr[u].rs].add += tr[u].add;
            tr[tr[u].ls].max += tr[u].add;
            tr[tr[u].rs].max += tr[u].add;
            tr[u].add = 0;
        }

        private void pushup(int u) {
            tr[u].max = Math.max(tr[tr[u].ls].max, tr[tr[u].rs].max);
        }

        public int book(int start, int end) {
            update(1, 1, N + 1, start + 1, end, 1);
            return query(1, 1, N + 1, 1, N + 1);
        }
    }

    // 参考：https://leetcode.cn/problems/my-calendar-iii/solution/wo-de-ri-cheng-an-pai-biao-iii-by-leetco-9rif/
    private class MyCalendarThree2{

        private TreeMap<Integer, Integer> cnt;

        public MyCalendarThree2() {
            cnt = new TreeMap<Integer, Integer>();
        }

        public int book(int start, int end) {

            int ans = 0;
            int maxBook = 0;
            cnt.put(start, cnt.getOrDefault(start, 0) + 1);
            cnt.put(end, cnt.getOrDefault(end, 0) - 1);

            for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                int freq = entry.getValue();
                maxBook += freq;
                ans = Math.max(maxBook, ans);
            }

            return ans;
        }

    }

}
