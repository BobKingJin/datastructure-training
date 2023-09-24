package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-18 14:54
 */
public class Number933 {

    private class RecentCounter {

        private class Node {
            // ls 和 rs 分别代表当前节点（区间）的左右子节点在 tr 的下标
            // val 代表在当前节点（区间）所包含的数的个数
            int ls;
            int rs;
            int val;
        }

        public RecentCounter() {

        }

        int N = (int)1e9;
        int M = 800010;
        int idx = 1;
        Node[] tr = new Node[M];

        void update(int u, int lc, int rc, int x, int v) {
            if (lc == x && rc == x) {
                tr[u].val += (rc - lc + 1) * v;
                return ;
            }

            lazyCreate(u);
            int mid = lc + rc >> 1;

            if (x <= mid) {
                update(tr[u].ls, lc, mid, x, v);
            } else {
                update(tr[u].rs, mid + 1, rc, x, v);
            }
            pushup(u);
        }

        int query(int u, int lc, int rc, int l, int r) {
            if (l <= lc && rc <= r)
                return tr[u].val;

            lazyCreate(u);
            int mid = lc + rc >> 1;
            int ans = 0;
            if (l <= mid)
                ans = query(tr[u].ls, lc, mid, l, r);
            if (r > mid)
                ans += query(tr[u].rs, mid + 1, rc, l, r);
            return ans;
        }

        void lazyCreate(int u) {
            if (tr[u] == null)
                tr[u] = new Node();

            if (tr[u].ls == 0) {
                tr[u].ls = ++idx;
                tr[tr[u].ls] = new Node();
            }

            if (tr[u].rs == 0) {
                tr[u].rs = ++idx;
                tr[tr[u].rs] = new Node();
            }
        }

        void pushup(int u) {
            tr[u].val = tr[tr[u].ls].val + tr[tr[u].rs].val;
        }

        // 参考：https://leetcode.cn/problems/number-of-recent-calls/solution/by-ac_oier-evqe/
        // 参考：https://sharingsource.github.io/2022/05/06/933.%20最近的请求次数 (简单) /
        public int ping(int t) {
            update(1, 1, N, t, 1);
            return query(1, 1, N, Math.max(0, t - 3000), t);
        }
    }
}
