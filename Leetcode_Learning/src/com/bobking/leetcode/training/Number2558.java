package com.bobking.leetcode.training;

/**
 * @Date: 2023/12/13 7:55
 * @Author: BobKing
 * @Description:
 */
public class Number2558 {

    int[] heap = new int[10010];
    int sz = 0;

    // 参考: https://leetcode.cn/problems/take-gifts-from-the-richest-pile/solutions/2501825/gong-shui-san-xie-ke-shi-hua-shou-xie-du-pp3a/?envType=daily-question&envId=2023-12-13
    public long pickGifts(int[] gifts, int k) {

        for (int x : gifts)
            add(-x);

        while (k-- > 0)
            add(-(int)Math.sqrt(-pop()));

        long ans = 0;
        // 没必要再维持堆的有序, 直接读取累加
        while (sz != 0)
            ans += -heap[sz--];

        return ans;
    }

    private void swap(int a, int b) {
        int c = heap[a];
        heap[a] = heap[b];
        heap[b] = c;
    }

    private void up(int u) {
        // 将「当前节点 i」与「父节点 i / 2」进行比较, 若父节点值更大, 则进行交换
        int fa = u / 2;
        if (fa != 0 && heap[fa] > heap[u]) {
            swap(fa, u);
            up(fa);
        }
    }

    private void down(int u) {
        // 将当「前节点 cur」与「左节点 l」及「右节点 r」进行比较, 找出最小值, 若当前节点不是最小值, 则进行交换
        int cur = u;
        int l = u * 2;
        int r = u * 2 + 1;
        if (l <= sz && heap[l] < heap[cur])
            cur = l;
        if (r <= sz && heap[r] < heap[cur])
            cur = r;
        if (cur != u) {
            swap(cur, u);
            down(cur);
        }
    }

    private void add(int x) {
        heap[++sz] = x;
        up(sz);
    }

    private int peek() {
        return heap[1];
    }

    private int pop() {
        int ans = peek();
        heap[1] = heap[sz--];
        down(1);
        return ans;
    }

}
